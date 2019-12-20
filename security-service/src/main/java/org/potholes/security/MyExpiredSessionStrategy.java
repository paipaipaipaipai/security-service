package org.potholes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.potholes.api.ResponseStatus;
import org.potholes.utils.JsonUtil;
import org.potholes.utils.ResponseUtils;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

/***
 * SESSION 过期决策
 */
@Component
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.objectToJsonStr(ResponseUtils.buildResponse(ResponseStatus.TOKEN_INVALID)));
        out.flush();
        out.close();
    }

}
