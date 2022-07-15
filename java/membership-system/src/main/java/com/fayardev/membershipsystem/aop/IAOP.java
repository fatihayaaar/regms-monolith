package com.fayardev.membershipsystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

public interface IAOP {

    @Before("execution(* com.fayardev.membershipsystem.repositories.abstracts.IProfileRepository.*(..))")
    void before(JoinPoint joinPoint);

    @After("execution(* com.fayardev.membershipsystem.repositories.abstracts.IProfileRepository.*(..))")
    void after(JoinPoint joinPoint);
}
