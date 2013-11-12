/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

/**
 * Synchronized statistics counter
 * @author Siarhei Skavarodkin
 */
public class StatisticsCounter {
    
    private long currentValue;
    private long previousValue;
    private long currentLoad;

    /**
     * get current value of counter
     * @return current value of counter
     */
    public synchronized long getCurrentValue() {
        return currentValue;
    }
    
    /**
     * increase current value of counter on 1.
     * In case if current value is {@link Long#MAX_VALUE} then value is set to 0
     */
    public synchronized void stepCounter () {
        if (currentValue == Long.MAX_VALUE) {
            currentValue = 0;
        } else {
            currentValue++;
        }
    }
    
    /**
     * set current value of counter to 0
     */
    public synchronized void resetCounter() {
        currentValue = 0;
    }

    /**
     * get current load. Please note that current load is calculated by
     * {@link StatisticsCounter#updateCurrentLoad() updateCurrentLoad} method
     * @return current load
     */
    public synchronized long getCurrentLoad() {
        return currentLoad;
    }
    
    /**
     * Call this method to update "current load".
     * By "current load" the following is meant:
     * difference between current value of counter and value of counter from
     * previous call of this method.
     * <br/>
     * If this method is called first time or after 
     * {@link StatisticsCounter#resetCounter() }, then current value is used as 
     * "current load"
     * <br/>
     * To retrieve value of "current load" call
     * {@link StatisticsCounter#getCurrentLoad() getCurrentLoad} method
     */
    public synchronized void updateCurrentLoad() {
        if (previousValue > currentValue) { //if counter was reseted
            currentLoad = currentValue;
        } else {
            currentLoad = currentValue - previousValue;
        }
        previousValue = currentValue;
    }
    
}
