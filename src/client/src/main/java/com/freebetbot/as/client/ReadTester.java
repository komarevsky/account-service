/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * tests getAmount method of AccountService
 * @author Siarhei Skavarodkin
 */
public class ReadTester extends ServiceTester {

    private static final Logger LOGGER = Logger.getLogger(ReadTester.class);
    
    /**
     * constructor
     * @param service
     * @param idList
     * @param threadName 
     */
    public ReadTester(AccountService service, List<Integer> idList, String threadName) {
        super(service, idList, threadName);
    }
    
    @Override
    protected void callServiceMethod(Integer id) {
        try {
            LOGGER.debug("send getAmount from " + threadName + " with id=" + id);
            Long amount = service.getAmount(id);
            String msg = "received amount= " + amount + " with id=" + id
                    + " from " + threadName;
            LOGGER.debug(msg);
        } catch (AccountServiceException ex) {
            LOGGER.error("error in " + threadName, ex);
        }
    }
    
    
}
