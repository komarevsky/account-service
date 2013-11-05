/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * tests addAmount method of AccountService
 * @author Siarhei Skavarodkin
 */
public class WriteTester extends ServiceTester {

    private static final Logger LOGGER = Logger.getLogger(WriteTester.class);
    
    private final Random random;
    
    /**
     * constructor
     * @param service
     * @param idList
     * @param threadName 
     */
    public WriteTester(AccountService service, List<Integer> idList, String threadName) {
        super(service, idList, threadName);
        random = new Random();
    }
    
    @Override
    protected void callServiceMethod(Integer id) {
        try {
            Long amount = random.nextLong();
            LOGGER.debug("send addAmount from " + threadName + " with id=" 
                    + id + " amount=" + amount);
            service.addAmount(id, amount);
            LOGGER.debug("executed addAmount from " + threadName + " with id=" 
                    + id + " amount=" + amount);
        } catch(AccountServiceException ex) {
            LOGGER.error("error in " + threadName, ex);
        }
    }
    
    
}
