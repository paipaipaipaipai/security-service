package org.potholes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.api.ResponseStatus;
import org.potholes.utils.JsonUtil;
import org.potholes.utils.ResponseUtils;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

/***
 * SESSION 失效决策
 */
@Component
public class MyInvalidSessionStrategy implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.objectToJsonStr(ResponseUtils.buildResponse(ResponseStatus.TOKEN_INVALID)));
        out.flush();
        out.close();
    }

}
