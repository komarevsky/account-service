/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service;

import com.caucho.hessian.server.HessianServlet;
import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class AccountServiceFacade extends HessianServlet implements AccountService {

    private final AccountServiceManager asManager;

    public AccountServiceFacade() {
        this.asManager = new AccountServiceManager();
    }
    
    @Override
    public Long getAmount(Integer id) throws AccountServiceException {
        return asManager.getAmount(id);
    }

    @Override
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        asManager.addAmount(id, value);
    }
    
}
