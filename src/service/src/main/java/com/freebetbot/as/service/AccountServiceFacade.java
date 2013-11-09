/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service;

import com.freebetbot.as.service.statistics.StatisticsManager;
import com.freebetbot.as.service.account.AccountServiceManager;
import com.caucho.hessian.server.HessianServlet;
import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import com.freebetbot.as.service.statistics.StepGetAmountCounter;

/**
 * Facade for the whole logic of service
 * @author Siarhei Skavarodkin
 */
public class AccountServiceFacade extends HessianServlet implements AccountService {

    private final AccountServiceManager asManager;
    private final StatisticsManager statisticsManager;

    public AccountServiceFacade() {
        this.statisticsManager = StatisticsManager.getInstance();
        this.asManager = new AccountServiceManager(statisticsManager);
    }
    
    @Override
    @StepGetAmountCounter
    public Long getAmount(Integer id) throws AccountServiceException {
        return asManager.getAmount(id);
    }

    @Override
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        asManager.addAmount(id, value);
    }
}
