package com.spring.instagram.login;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class SessionChecker {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Resource
    private UserInfo userInfo;

//    @Around("execution(* com.spring.instagram.login.LoginService.AmiLogin(..)) && args(userName,..)")
    @Around("@annotation(com.spring.instagram.login.SessionCheck) && args(userName,..)")
//    expression="execution(* package1.*.*(..)) || execution(* package2.*.*(..))"
//    https://stackoverflow.com/a/8427769
    public Object around(ProceedingJoinPoint joinPoint, String userName) throws Throwable {
        log.info("[around] checking session before execute method");
        Object[] signatureArgs = joinPoint.getArgs();
        if (userInfo.getUserNm() != null) {
            log.info("[SessionChecker] [around] Hi, " + userInfo.getUserNm());
            signatureArgs[0] = userInfo.getUserNm();
            Object result = joinPoint.proceed(signatureArgs);
            return result;
        }
        log.info("[around] Not logged in");
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "NOT LOGGED-IN");
        //throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "NO_LOGIN") {};
        return null;
    }
}
