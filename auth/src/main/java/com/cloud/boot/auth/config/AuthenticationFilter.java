package com.cloud.boot.auth.config;

import cn.hutool.core.util.StrUtil;
import com.cloud.boot.auth.service.UserDetailsVo;
import com.cloud.boot.auth.util.JwtUtil;
import com.cloud.boot.common.core.util.JacksonUtil;
import com.cloud.boot.common.core.util.R;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

/**
 * @author lhd
 */
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final List<String> PUBLIC_PATHS = List.of("/login");
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isPublicPath(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        if (StrUtil.isBlank(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            sendErrorResponse(response, "token格式错误");
            return;
        }

        String token = authorizationHeader.replaceFirst("Bearer ", "");
        String username = jwtUtil.getUsername(token);
        if (StrUtil.isBlank(username)) {
            sendErrorResponse(response, "token解析错误");
            return;
        }

        if (!jwtUtil.validateToken(token)) {
            sendErrorResponse(response, "token已过期");
            return;
        }

        UserDetailsVo userDetailsVo = (UserDetailsVo) userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetailsVo, null, userDetailsVo.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String requestUri) {
        return PUBLIC_PATHS.stream().anyMatch(requestUri::startsWith);
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JacksonUtil.toJsonStr(R.fail(10000, errorMessage)));
    }
}