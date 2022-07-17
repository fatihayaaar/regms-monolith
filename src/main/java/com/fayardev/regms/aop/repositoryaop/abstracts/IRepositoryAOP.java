package com.fayardev.regms.aop.repositoryaop.abstracts;

import org.aspectj.lang.JoinPoint;

public interface IRepositoryAOP {

    void before(JoinPoint joinPoint);

    void after(JoinPoint joinPoint);
}
