/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import org.apache.log4j.Logger;

/**
 * Manager for Statistics for Account Service.
 * Gives the possibility to manage by the following counters:
 * 1. totalCallsGetAmount
 * 2. totalCallsAddAmount
 * 3. currentlyServedGetAmount
 * 4. currentlyServedAddAmount
 * @author Siarhei Skavarodkin
 */
public class StatisticsManager {
    
    private static final Logger LOGGER = Logger.getLogger(StatisticsManager.class);
    
    private long totalCallsGetAmount;
    private long totalCallsAddAmount;
    private long currentlyServedGetAmount;
    private long currentlyServedAddAmount;
    
    public StatisticsManager() {
    }
    
    /**
     * Resets totalCallsGetAmount and totalCallsAddAmount counter
     */
    public void resetStatistics() {
        totalCallsGetAmount = 0;
        totalCallsAddAmount = 0;
    }
    
    /**
     * stores current counter values to log
     */
    public void storeCountersToLog() {
        LOGGER.info(this.toString());
    }
    
    /**
     * increases currentlyServedGetAmount and totalCallsGetAmount counters
     */
    public void incGetAmountCounter() {
        ++currentlyServedGetAmount;
        ++totalCallsGetAmount;
    }
    
    /**
     * decreases currentlyServedGetAmount counters
     */
    public void decGetAmountCounter() {
        --currentlyServedGetAmount;
    }

    /**
     * increases currentlyServedAddAmount and totalCallsAddAmount counters
     */
    public void incAddAmountCounter() {
        ++currentlyServedAddAmount;
        ++totalCallsAddAmount;
    }
    
    /**
     * decreases currentlyServedAddAmount counters
     */
    public void decAddAmountCounter() {
        --currentlyServedAddAmount;
    }

    @Override
    public String toString() {
        return "StatisticsManager{\ntotalCallsGetAmount=" + totalCallsGetAmount
                + "\ntotalCallsAddAmount=" + totalCallsAddAmount 
                + "\ncurrentlyServedGetAmount=" + currentlyServedGetAmount 
                + "\ncurrentlyServedAddAmount=" + currentlyServedAddAmount 
                + "\n}";
    }
    
}
