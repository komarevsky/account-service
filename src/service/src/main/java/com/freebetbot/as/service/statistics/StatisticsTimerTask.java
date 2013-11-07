/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import java.util.TimerTask;

/**
 * Stores statistics logs at each timer event
 * @author Siarhei Skavarodkin
 */
public class StatisticsTimerTask extends TimerTask {

    private final StatisticsManager manager;
    
    public StatisticsTimerTask(StatisticsManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void run() {
        manager.storeCountersToLog();
    }
    
    
}
