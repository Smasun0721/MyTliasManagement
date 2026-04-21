package org.example.tliaswebmanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.util.JwtUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI();

        //2.判断是否是登录请求
        if (requestURI.contains("/login")) {
            log.info("登录请求，直接放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断令牌是否为空
        if (token == null || token.isEmpty()) {
            log.info("令牌为空,拒绝放行");
            response.setStatus(401);
            return;
        }

        //5.解析令牌判断是否合法
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法,拒绝放行");
            response.setStatus(401);
            return;
        }

        //6.校验通过，放行
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
