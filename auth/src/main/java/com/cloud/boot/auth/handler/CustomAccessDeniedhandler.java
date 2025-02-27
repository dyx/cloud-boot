package com.cloud.boot.auth.handler;

import com.cloud.boot.common.core.util.JacksonUtil;
import com.cloud.boot.common.core.util.R;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * @author lhd
 * 自定义访问禁止错误
 */
public class CustomAccessDeniedhandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JacksonUtil.toJsonStr(R.fail(10000, accessDeniedException.getMessage())));
    }
}
