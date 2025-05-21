package com.example.tilas.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.tilas.pojo.Result;
import com.example.tilas.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");

        // 获取url
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = httpServletRequest.getRequestURL().toString();
        log.info("url: " + url);

        // 判断请求
        if (url.contains("login")) {
            log.info("登录操作，直接放行");
            // 直接放行请求
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 获取令牌
        String jwt = httpServletRequest.getHeader("token");
        if (jwt.length() == 0) {
            log.info("token为空");
            // 按照需求文档构造Result
            Result errorResult = Result.error("NOT_LOGIN");
            // 把Result对象搞成String，需要依赖fastjson
            String tmpResult = JSONObject.toJSONString(errorResult);
            // 直接把String写回浏览器
            httpServletResponse.getWriter().write(tmpResult);
            return;
        }

        // 校验令牌
        try {
            JwtUtils.parseJwt(jwt);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } catch (Exception e) {
            log.info("令牌非法");
            Result errorResult = Result.error("NOT_LOGIN");
            String tmpResult = JSONObject.toJSONString(errorResult);
            httpServletResponse.getWriter().write(tmpResult);
            return;
        }
    }
}
