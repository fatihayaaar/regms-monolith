package com.fayardev.regms.aop.repositoryaop;

import com.fayardev.regms.aop.logging.RepositoryLogging;
import com.fayardev.regms.aop.repositoryaop.abstracts.IRepositoryAOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ProfileRepositoryAOP implements IRepositoryAOP {

    @Override
    @Before("execution(* com.fayardev.regms.repositories.ProfileRepository.*(..))")
    public void before(JoinPoint joinPoint) {
        RepositoryLogging.logBefore(joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @Override
    @After("execution(* com.fayardev.regms.repositories.ProfileRepository.*(..))")
    public void after(JoinPoint joinPoint) {
    }
}
