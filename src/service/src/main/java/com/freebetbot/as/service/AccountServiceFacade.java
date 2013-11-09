/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service;

import com.freebetbot.as.service.account.AccountServiceManager;
import com.caucho.hessian.server.HessianServlet;
import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import com.freebetbot.as.service.statistics.StepAddAmountCounter;
import com.freebetbot.as.service.statistics.StepGetAmountCounter;

/**
 * Facade for the whole logic of service
 * @author Siarhei Skavarodkin
 */
public class AccountServiceFacade extends HessianServlet implements AccountService {

    private final AccountServiceManager asManager;

    public AccountServiceFacade() {
        this.asManager = new AccountServiceManager();
    }
    
    @Override
    @StepGetAmountCounter
    public Long getAmount(Integer id) throws AccountServiceException {
        return asManager.getAmount(id);
    }

    @Override
    @StepAddAmountCounter
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        asManager.addAmount(id, value);
    }
}
