package org.potholes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.potholes.utils.JsonUtil;
import org.potholes.utils.ResponseUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/***
 * 认证失败
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            out.write(JsonUtil.objectToJsonStr(ResponseUtils.buildErrorResponse("用户名或密码错误")));
        } else if (exception instanceof DisabledException) {
            out.write(JsonUtil.objectToJsonStr(ResponseUtils.buildErrorResponse("账号已禁用")));
        } else {
            out.write(JsonUtil.objectToJsonStr(ResponseUtils.buildErrorResponse("操作失败")));
        }
        out.flush();
        out.close();
    }

}
