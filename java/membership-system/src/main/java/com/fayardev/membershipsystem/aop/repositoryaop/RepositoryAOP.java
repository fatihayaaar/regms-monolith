package com.fayardev.membershipsystem.aop.repositoryaop;

import com.fayardev.membershipsystem.aop.repositoryaop.abstracts.IRepositoryAOP;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class RepositoryAOP implements IRepositoryAOP {

    @Override
    @Before("execution(* com.fayardev.membershipsystem.repositories.abstracts.IRepository.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName() + " metodu çağrıldı");
    }

    @Override
    @After("execution(* com.fayardev.membershipsystem.repositories.abstracts.IRepository.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName() + " metodu çağrıldı");
    }
}
