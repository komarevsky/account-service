/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for work with statistics counters
 * @author Siarhei Skavarodkin
 */
@Aspect
public class StatisticsAspect {

    private static final Logger LOGGER = Logger.getLogger(StatisticsAspect.class);
    
    @Pointcut("@annotation(com.freebetbot.as.service.statistics.StepGetAmountCounter)")
    public void stepGetAmountMethod() { }
    
    @Around("stepGetAmountMethod()")
    public Object stepGetAmountCall(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.trace("stepGetAmountCall");
        StatisticsManager manager = StatisticsManager.getInstance();
        try {
            manager.incGetAmountCounter();
            Object result = joinPoint.proceed();
            manager.decGetAmountCounter();
            return result;
        } catch (Throwable t) {
            manager.decGetAmountCounter();
            throw t;
        }
    }
}
