/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import com.freebetbot.as.service.support.ThreadInfo;
import java.util.Timer;
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
    private static final long TIMER_PERIOD = 300 * 1000; // 5min
    private static StatisticsManager instance;
    
    private final Timer timer;
    
    private volatile long totalCallsGetAmount;
    private volatile long totalCallsAddAmount;
    private volatile long currentlyServedGetAmount;
    private volatile long currentlyServedAddAmount;
    
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
        LOGGER.info("StatisticsManager constructor:\n" 
                + new ThreadInfo().getThreadInfo());
        StatisticsTimerTask timerTask = new StatisticsTimerTask(this);
        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, TIMER_PERIOD);
    }

    /**
     * get value of TotalCallsGetAmount counter
     * @return value of TotalCallsGetAmount counter
     */
    public long getTotalCallsGetAmount() {
        return totalCallsGetAmount;
    }

    /**
     * get value of TotalCallsAddAmount counter
     * @return value of TotalCallsAddAmount counter
     */
    public long getTotalCallsAddAmount() {
        return totalCallsAddAmount;
    }

    /**
     * get value of CurrentlyServedGetAmount counter
     * @return value of CurrentlyServedGetAmount counter
     */
    public long getCurrentlyServedGetAmount() {
        return currentlyServedGetAmount;
    }

    /**
     * get value of CurrentlyServedAddAmount counter
     * @return value of CurrentlyServedAddAmount counter
     */
    public long getCurrentlyServedAddAmount() {
        return currentlyServedAddAmount;
    }
    
    /**
     * Resets totalCallsGetAmount and totalCallsAddAmount counters
     */
    public void resetCounters() {
        totalCallsGetAmount = 0;
        totalCallsAddAmount = 0;
        LOGGER.info("Statistics counters reset has been invoked");
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
