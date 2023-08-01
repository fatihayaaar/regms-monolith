package com.fayardev.regms.aop.validateaop;

import com.fayardev.regms.aop.validateaop.abstracts.IValidateAOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ProfileValidateAOP implements IValidateAOP {

    @Override
    @AfterThrowing(value = "execution(* com.fayardev.regms.validates.ProfileValidate.*(..))", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {

    }
}
