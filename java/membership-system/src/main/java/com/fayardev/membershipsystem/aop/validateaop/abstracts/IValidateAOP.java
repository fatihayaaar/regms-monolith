package com.fayardev.membershipsystem.aop.validateaop.abstracts;

import org.aspectj.lang.JoinPoint;
public interface IValidateAOP {

    void afterThrowing(JoinPoint joinPoint, Throwable error);
}
