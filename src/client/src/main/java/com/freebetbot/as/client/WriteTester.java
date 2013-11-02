/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.freebetbot.as.api.AccountService;
import java.util.List;
import java.util.Random;

/**
 * tests addAmount method of AccountService
 * @author Siarhei Skavarodkin
 */
public class WriteTester extends ServiceTester {

    private final Random random;
    
    public WriteTester(AccountService service, List<Integer> idList) {
        super(service, idList);
        random = new Random();
    }
    
    @Override
    protected void callServiceMethod(Integer id) {
        service.addAmount(id, random.nextLong());
    }
    
    
}
