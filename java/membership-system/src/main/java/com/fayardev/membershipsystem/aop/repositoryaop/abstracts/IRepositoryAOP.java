package com.fayardev.membershipsystem.aop.repositoryaop.abstracts;

import org.aspectj.lang.JoinPoint;

public interface IRepositoryAOP {

    void before(JoinPoint joinPoint);

    void after(JoinPoint joinPoint);
}
