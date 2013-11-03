/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.freebetbot.as.api.AccountService;
import java.util.List;
import java.util.Random;

/**
 * Base for classes who want to test different AccountService methods:
 * just override callServiceMethod and call service methods inside
 * @author Siarhei Skavarodkin
 */
public abstract class ServiceTester implements Runnable {

    protected final AccountService service;
    protected final List<Integer> idList;

    public ServiceTester(AccountService service, List<Integer> idList) {
        this.service = service;
        this.idList = idList;
    }
    
    @Override
    public void run() {
        if (idList.isEmpty()) {
            return;
        }
        
        while (true) {
            Random r = new Random();
            int idIndex = r.nextInt(idList.size());
            Integer id = idList.get(idIndex);
            try {
                callServiceMethod(id);
            } catch (Throwable ex) {
                System.err.println(ex.toString());
            }
        }
    }
    
    protected abstract void callServiceMethod(Integer id);
    
}
