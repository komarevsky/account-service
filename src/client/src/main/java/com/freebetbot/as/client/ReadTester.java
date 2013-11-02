/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.freebetbot.as.api.AccountService;
import java.util.List;

/**
 * tests getAmount method of AccountService
 * @author Siarhei Skavarodkin
 */
public class ReadTester extends ServiceTester {

    public ReadTester(AccountService service, List<Integer> idList) {
        super(service, idList);
    }
    
    @Override
    protected void callServiceMethod(Integer id) {
        service.getAmount(id);
    }
    
    
}
