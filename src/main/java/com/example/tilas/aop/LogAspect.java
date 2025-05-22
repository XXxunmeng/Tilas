package com.example.tilas.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.tilas.mapper.OperateLogMapper;
import com.example.tilas.pojo.OperateLog;
import com.example.tilas.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;
    // 用于记录操作日志的切面类
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.example.tilas.anno.Log)") // 切入点表达式（就是指定了哪些方法要用）
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 记录开始时间
        long start = System.currentTimeMillis();

        // 调用原始方法
        Object result = proceedingJoinPoint.proceed(); // 这是原始方法的返回值，最后要返回回去

        // 记录结束时间
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature() + "方法执行耗时为：" + (end - start));


        // 获取操作人的id（就是当前登录员工）
        String jwt = request.getHeader("token"); // 依赖注入可还行
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        // 获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        String arg = Arrays.toString(args);

        // 获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        // 获取返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        Long t = end - start;

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, arg, returnValue, t);

        operateLogMapper.insert(operateLog);

        return result; // 把原始方法的返回值返回
    }

}
