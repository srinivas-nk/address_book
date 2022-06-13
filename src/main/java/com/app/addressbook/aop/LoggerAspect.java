package com.app.addressbook.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.app.addressbook.controller.*Controller.*(..))")
    public Object controllerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("request: {}", joinPoint.getArgs());
        log.info("method execution : " + joinPoint.getSignature().getName());
        ResponseEntity<Object> response = (ResponseEntity<Object>) joinPoint.proceed();
        log.info("response: {}",response);
        return response;
    }

    @Around("execution(* com.app.addressbook.service.*Service.*(..))")
    public Object serviceLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("class: {}", joinPoint.getSignature().getDeclaringType().getSimpleName());
        log.info("method:{} arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
        return joinPoint.proceed();
    }
}
