/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.controller;

import com.freebetbot.as.service.statistics.StatisticsManager;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Statistics
 * @author Siarhei Skavarodkin
 */
@Controller
public class StiatisticsController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String statisticsIndexGet(Map<String, Object> model) {
        StatisticsManager manager = StatisticsManager.getInstance();        
        putCountersToModel(manager, model);
        return "statistics";
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String statisticsIndexPost(Map<String, Object> model) {
        StatisticsManager manager = StatisticsManager.getInstance();        
        putCountersToModel(manager, model);
        return "statistics";
    }
    
    @RequestMapping(value = "reset/current", method = RequestMethod.POST)
    public String resetCurrentCountersPost(Map<String, Object> model) {
        StatisticsManager manager = StatisticsManager.getInstance();        
        manager.resetCurrentInvocationCounters();
        putCountersToModel(manager, model);
        return "statistics-reset";
    }

    @RequestMapping(value = "reset/total", method = RequestMethod.POST)
    public String resetTotalCountersPost(Map<String, Object> model) {
        StatisticsManager manager = StatisticsManager.getInstance();        
        manager.resetTotalInvocationCounters();
        putCountersToModel(manager, model);
        return "statistics-reset";
    }

    @RequestMapping(value = "store", method = RequestMethod.POST)
    public String storeStatisticsPost(Map<String, Object> model) {
        StatisticsManager manager = StatisticsManager.getInstance();        
        manager.storeCountersToLog();
        putCountersToModel(manager, model);
        return "statistics-store";
    }
    
    private void putCountersToModel(StatisticsManager manager,
            Map<String, Object> model) {
        model.put("currentlyServedGetAmount", manager.getCurrentlyServedGetAmount());
        model.put("currentlyServedAddAmount", manager.getCurrentlyServedAddAmount());
        model.put("totalCallsGetAmount", manager.getTotalCallsGetAmount());
        model.put("totalCallsAddAmount", manager.getTotalCallsAddAmount());
    }

}
