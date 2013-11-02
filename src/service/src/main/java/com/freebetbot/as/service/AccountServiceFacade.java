/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service;

import com.caucho.hessian.server.HessianServlet;
import com.freebetbot.as.api.AccountService;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class AccountServiceFacade extends HessianServlet implements AccountService {

    public Long getAmount(Integer id) {
        //TODO: getAmount logic
        return 0L;
    }

    public void addAmount(Integer id, Long value) {
        //TODO: addAmount logic
    }
    
}
