package com.fayardev.membershipsystem.aop.repositoryaop;

import com.fayardev.membershipsystem.aop.repositoryaop.abstracts.IRepositoryAOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class RepositoryAOP implements IRepositoryAOP {

    @Override
    @Before("execution(* com.fayardev.membershipsystem.repositories.abstracts.IRepository.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }

    @Override
    @After("execution(* com.fayardev.membershipsystem.repositories.abstracts.IRepository.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }
}
