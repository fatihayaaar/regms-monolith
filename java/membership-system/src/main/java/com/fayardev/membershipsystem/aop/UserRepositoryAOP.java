package com.fayardev.membershipsystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserRepositoryAOP implements IAOP {

    @Override
    @Before("execution(* com.fayardev.membershipsystem.repositories.abstracts.IUserRepository.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }

    @Override
    @After("execution(* com.fayardev.membershipsystem.repositories.abstracts.IUserRepository.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }
}
