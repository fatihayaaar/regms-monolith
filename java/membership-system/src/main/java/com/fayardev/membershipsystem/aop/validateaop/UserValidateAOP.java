package com.fayardev.membershipsystem.aop.validateaop;

import com.fayardev.membershipsystem.aop.validateaop.abstracts.IValidateAOP;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class UserValidateAOP implements IValidateAOP {

    @Override
    @AfterThrowing(value = "execution(* com.fayardev.membershipsystem.validates.UserValidate.*(..))", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {

    }
}
