/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.freebetbot.as.api.AccountService;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * Base for classes who want to test different AccountService methods:
 * just override callServiceMethod and call service methods inside
 * @author Siarhei Skavarodkin
 */
public abstract class ServiceTester implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ServiceTester.class);
    
    protected final AccountService service;
    protected final List<Integer> idList;
    protected final String threadName;
    
    /**
     * Construtor.
     * @param service Account Service
     * @param idList list with range of account Ids to test
     * @param threadName name of this thread (used in logging)
     */
    public ServiceTester(AccountService service, List<Integer> idList, String threadName) {
        this.service = service;
        this.idList = idList;
        this.threadName = threadName;
    }
    
    /**
     * starts send requests to Account Server
     */
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
                LOGGER.error("error in " + threadName, ex);
            }
        }
    }
    
    /**
     * method which implements specific request to Account Service
     * @param id account id
     */
    protected abstract void callServiceMethod(Integer id);
    
}
