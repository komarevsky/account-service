/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
    
    @Pointcut("@annotation(com.freebetbot.as.service.statistics.StepAddAmountCounter)")
    public void stepAddAmountMethod() { }
    
    @Before("stepGetAmountMethod()")
    public void beforeGetAmountCall() {
        LOGGER.trace("beforeStepGetAmountCall");
        StatisticsManager.getInstance().incGetAmountCounter();
    }
    
    @Before("stepAddAmountMethod()")
    public void beforeAddAmountCall() {
        LOGGER.trace("beforeStepAddAmountCall");
        StatisticsManager.getInstance().incAddAmountCounter();
    }
    
}
