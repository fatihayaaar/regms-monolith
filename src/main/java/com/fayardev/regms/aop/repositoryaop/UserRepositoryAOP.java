package com.fayardev.regms.aop.repositoryaop;

import com.fayardev.regms.aop.logging.RepositoryLogging;
import com.fayardev.regms.aop.repositoryaop.abstracts.IRepositoryAOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserRepositoryAOP implements IRepositoryAOP {

    @Override
    @Before("execution(* com.fayardev.regms.repositories.UserRepository.*(..))")
    public void before(JoinPoint joinPoint) {
        RepositoryLogging.logBefore(joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @Override
    @After("execution(* com.fayardev.regms.repositories.UserRepository.*(..))")
    public void after(JoinPoint joinPoint) {
    }

    @AfterReturning(value = "execution(* com.fayardev.regms.repositories.UserRepository.*(..))", returning = "val")
    public void afterReturning(JoinPoint joinPoint, Object val) {
    }
}
