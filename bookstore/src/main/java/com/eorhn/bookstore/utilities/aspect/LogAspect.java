package com.eorhn.bookstore.utilities.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    /**
     * Simple aspect for logging method inputs and outputs to terminal.
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "within(com.eorhn..*.service..*)")
    public Object logServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        System.out.println("method name :" + methodName);
        StringBuffer parameterList = new StringBuffer();
        Object[] arguments = proceedingJoinPoint.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Object parameter : arguments){
            parameterList.append("Parameter : " + objectMapper.writeValueAsString(parameter));
        }
        System.out.println(parameterList.toString());

        Object result = proceedingJoinPoint.proceed();
        System.out.println("Result : " +objectMapper.writeValueAsString(result));
        return result;
    }
}
