package com.wx.wxsecuritydemo.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * User: wangxiang
 * Date: 2019/9/10
 */
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.wx.wxsecuritydemo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //可以获得方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("方法的参数：" + args[i]);
        }
        Long start = new Date().getTime();
        //proceed即为方法的返回值
        Object proceed = joinPoint.proceed();
        System.out.println("time aspect 耗时:" + ((new Date().getTime()) - start));
        return proceed;
    }
}
