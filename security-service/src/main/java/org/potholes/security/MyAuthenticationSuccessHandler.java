package org.potholes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.utils.JsonUtil;
import org.potholes.utils.ResponseUtils;
import org.potholes.utils.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/***
 * 认证成功
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.objectToJsonStr(ResponseUtils.buildSuccessResponse(UserUtil.getCurrentUser())));
        out.flush();
        out.close();
    }

}
