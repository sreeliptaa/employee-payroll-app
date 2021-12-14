package com.bridgelabz.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is Aspect Oriented Programming class which holds all the logging related application
 *
 * @author SREELIPTA
 * @since 2021-12-10
 */
@Component
@Aspect
@Slf4j
public class LoggingAdvice {

    /**
     * Purpose : This method is created to implement the logging mechanism through out the application
     */
    @Pointcut(value = "execution (* com.bridgelabz.*.*.*(..) )")
    public void myPointCut() {
    }

    /**
     * Purpose : This method is created to centralize the logging statement
     * to track the application flow through out the program
     *
     * @param proceedingJoinPoint : It manages the reflection of the program for getting the method details and input parameters
     * @return : The JSON format of logger statement before and after the advice
     * @throws Throwable : when there is any exception
     */
    @Around("myPointCut()")
    private Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        log.info("Method invoked: " + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object object = proceedingJoinPoint.proceed();
        log.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(array));
        return object;
    }
}
