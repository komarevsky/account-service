/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.controller;

import com.freebetbot.as.service.statistics.StatisticsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Statistics
 * @author Siarhei Skavarodkin
 */
@Controller
public class StiatisticsController {
                              
    @RequestMapping(value = "reset", method = RequestMethod.POST)
    public String resetStatisticsPost() {
        StatisticsManager.getInstance().resetStatistics();
        return "index";
    }
    
    @RequestMapping(value = "store", method = RequestMethod.POST)
    public String storeStatisticsPost() {
        StatisticsManager.getInstance().storeCountersToLog();
        return "index";
    }

}
