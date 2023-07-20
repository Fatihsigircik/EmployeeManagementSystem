package com.example.employeemanagementsystem.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.employeemanagementsystem.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before {} called with arguments: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @After("execution(* com.example.employeemanagementsystem.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("After {} called", joinPoint.getSignature().toShortString());
    }
}