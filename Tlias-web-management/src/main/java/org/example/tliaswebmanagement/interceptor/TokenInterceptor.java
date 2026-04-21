package org.example.tliaswebmanagement.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.util.CurrentHolder;
import org.example.tliaswebmanagement.util.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.获取请求路径
//        String requestURI = request.getRequestURI();
//
//        //2.判断是否是登录请求
//        if (requestURI.contains("/login")) {
//            log.info("登录请求，直接放行");
//            return true;
//        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断令牌是否为空
        if (token == null || token.isEmpty()) {
            log.info("令牌为空,拒绝放行");
            response.setStatus(401);
            return false;
        }

        //5.解析令牌判断是否合法
        try {
            Claims claims = JwtUtils.parseJWT(token);
            //将id存入ThreadLocal中
            Integer id = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(id);
        } catch (Exception e) {
            log.info("令牌非法,拒绝放行");
            response.setStatus(401);
            return false;
        }

        //6.校验通过，放行
        log.info("令牌合法,放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //7.删除ThreadLocal中的资源
        CurrentHolder.remove();
    }
}
