/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.statistics;

import java.util.TimerTask;

/**
 * Updates statistics load at each timer event
 * @author Siarhei Skavarodkin
 */
public class StatisticsUpdateLoadTimerTask extends TimerTask {

    private final StatisticsManager manager;
    
    public StatisticsUpdateLoadTimerTask(StatisticsManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void run() {
        manager.updateCurrentLoadCounters();
    }
    
}
