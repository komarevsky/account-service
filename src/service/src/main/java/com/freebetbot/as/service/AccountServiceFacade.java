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

    public Long getAmount(Integer id) throws AccountServiceException {
        //TODO: getAmount logic
        return 0L;
    }

    public void addAmount(Integer id, Long value) throws AccountServiceException {
        //TODO: addAmount logic
        throw new AccountServiceException("addAmount does not work for the moment");
    }
    
}
