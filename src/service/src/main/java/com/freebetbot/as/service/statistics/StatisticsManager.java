/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import java.util.Timer;
import org.apache.log4j.Logger;

/**
 * Manager for Statistics for Account Service.
 * Gives the possibility to manage by the following counters:
 * 1. totalCallsGetAmount
 * 2. totalCallsAddAmount
 * 3. currentLoadGetAmount
 * 4. currentLoadAddAmount
 * @author Siarhei Skavarodkin
 */
public class StatisticsManager {
    
    private static final Logger LOGGER = Logger.getLogger(StatisticsManager.class);
    private static final long STORE_TIMER_PERIOD = 300 * 1000; // 5min
    private static final long UPDATE_LOAD_TIMER_PERIOD = 1000; // 1sec
    
    private static StatisticsManager instance;
    
    private final Timer storeTimer;
    private final Timer updateLoadTimer;
    private final StatisticsCounter getAmountCounter;
    private final StatisticsCounter addAmountCounter;
    
    /**
     * singleton implementation
     * @return singleton instance of StatisticsManager
     */
    public static synchronized StatisticsManager getInstance() {
        if (instance == null) {
            instance = new StatisticsManager();
        }
        return instance;
    }
    
    /**
     * constructor
     */
    private StatisticsManager() {
        getAmountCounter = new StatisticsCounter();
        addAmountCounter = new StatisticsCounter();
        
        // timer for storing logs
        StatisticsStoreTimerTask storeTimerTask = new StatisticsStoreTimerTask(this);
        storeTimer = new Timer(true);
        storeTimer.scheduleAtFixedRate(storeTimerTask, 0, STORE_TIMER_PERIOD);
        
        // timer to update current load
        StatisticsUpdateLoadTimerTask updateLoadTimerTask =
                new StatisticsUpdateLoadTimerTask(this);
        updateLoadTimer = new Timer(true);
        updateLoadTimer.scheduleAtFixedRate(updateLoadTimerTask, 0, 
                UPDATE_LOAD_TIMER_PERIOD);
    }

    /**
     * get value of TotalCallsGetAmount counter
     * @return value of TotalCallsGetAmount counter
     */
    public long getTotalCallsGetAmount() {
        return getAmountCounter.getCurrentValue();
    }

    /**
     * get value of TotalCallsAddAmount counter
     * @return value of TotalCallsAddAmount counter
     */
    public long getTotalCallsAddAmount() {
        return addAmountCounter.getCurrentValue();
    }

    /**
     * get value of CurrentLoadGetAmount counter
     * @return value of CurrentLoadGetAmount counter
     */
    public long getCurrentLoadGetAmount() {
        return getAmountCounter.getCurrentLoad();
    }

    /**
     * get value of CurrentLoadAddAmount counter
     * @return value of CurrentLoadAddAmount counter
     */
    public long getCurrentLoadAddAmount() {
        return addAmountCounter.getCurrentLoad();
    }
    
    /**
     * Reset all counters
     */
    public void resetCounters() {
        getAmountCounter.resetCounter();
        addAmountCounter.resetCounter();
        LOGGER.info("Statistics counters reset has been invoked");
    }

    /**
     * stores current counter values to log
     */
    public synchronized void storeCountersToLog() {
        LOGGER.info(this.toString());
    }
    
    /**
     * updates values of currentLoadGetAmount and currentLoadAddAmount counters
     */
    public void updateCurrentLoadCounters() {
        getAmountCounter.updateCurrentLoad();
        addAmountCounter.updateCurrentLoad();
    }
    
    /**
     * increases totalCallsGetAmount counter
     */
    public void incGetAmountCounter() {
        getAmountCounter.stepCounter();
    }
    
    /**
     * increases totalCallsAddAmount counter
     */
    public void incAddAmountCounter() {
        addAmountCounter.stepCounter();
    }
    
    @Override
    public String toString() {
        return new StringBuffer("StatisticsManager{\n")
                .append("totalCallsGetAmount=")
                .append(getAmountCounter.getCurrentValue())
                .append("\ntotalCallsAddAmount=")
                .append(addAmountCounter.getCurrentValue())
                .append("\ncurrentLoadGetAmount=")
                .append(getAmountCounter.getCurrentLoad())
                .append("\ncurrentLoadAddAmount=")
                .append(addAmountCounter.getCurrentLoad())
                .append("\n}")
                .toString();
    }
    
}
