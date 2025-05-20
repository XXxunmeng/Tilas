package com.example.tilas.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.tilas.pojo.Result;
import com.example.tilas.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的URL
        String url = request.getRequestURL().toString();
        log.info("url: " + url);
        // 判断请求
        if (url.contains("login")) {
            log.info("登录操作，直接放行");
            // 直接放行请求
            return true;
        }

        // 获取令牌
        String jwt = request.getHeader("token");
        if (jwt == null || jwt.length() == 0) {
            log.info("token为空");
            // 按照需求文档构造Result
            Result errorResult = Result.error("NOT_LOGIN");
            // 把Result对象搞成String，需要依赖fastjson
            String tmpResult = JSONObject.toJSONString(errorResult);
            // 直接把String写回浏览器
            response.getWriter().write(tmpResult);
            return false;
        }

        // 校验令牌
        try {
            JwtUtils.parseJwt(jwt);
            return true;
        } catch (Exception e) {
            log.info("令牌非法");
            Result errorResult = Result.error("NOT_LOGIN");
            String tmpResult = JSONObject.toJSONString(errorResult);
            response.getWriter().write(tmpResult);
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
