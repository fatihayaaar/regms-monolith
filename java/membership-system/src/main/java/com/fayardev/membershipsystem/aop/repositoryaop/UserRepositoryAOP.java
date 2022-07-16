package com.fayardev.membershipsystem.aop.repositoryaop;

import com.fayardev.membershipsystem.aop.repositoryaop.abstracts.IRepositoryAOP;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class UserRepositoryAOP implements IRepositoryAOP {

    @Override
    @Before("execution(* com.fayardev.membershipsystem.repositories.abstracts.IUserRepository.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName() + " metodu çağrıldı");
    }

    @Override
    @After("execution(* com.fayardev.membershipsystem.repositories.abstracts.IUserRepository.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName() + " metodu çağrıldı");
    }

    @AfterReturning(value = "execution(* com.fayardev.membershipsystem.repositories.abstracts.IUserRepository.*(..))", returning = "val")
    public void afterReturning(JoinPoint joinPoint, Object val) {
        log.info(joinPoint.getSignature().getName() + " metodu çağrıldı");
    }
}
