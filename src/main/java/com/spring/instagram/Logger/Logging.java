package com.spring.instagram.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Before("execution(* com.spring.instagram.library.*.*(..))")
    public void beforePoint(JoinPoint joinPoint) {
        log.info("started: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.spring.instagram.library.*.*(..))")
    public void afterPoint(JoinPoint joinPoint) {
        log.info("ended: ");
    }

    @AfterReturning(pointcut = "execution(* com.spring.instagram.library.*.*(..))", returning = "returnValue")
    public void afterReturningPoint(Object returnValue) {
        log.info("after return");
    }

    @AfterThrowing(pointcut = "execution(* com.spring.instagram.library.*.*(..))", throwing = "exception")
    public void afterThrowing(Exception exception) {
        log.info("after throwing");
    }

    @Around("execution(* com.spring.instagram.library.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around started");
        Object result = joinPoint.proceed();
        log.info("around ended");
        return result;
    }
}
