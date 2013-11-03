/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service;

import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import java.util.HashMap;
import java.util.Map;

/**
 * manager
 * @author Siarhei Skavarodkin
 */
public class AccountServiceManager implements AccountService {

    private static final int CACHE_SIZE = 1000;
    
    // <id, amount>
    private Map<Integer, Long> cacheMap;
    
    public AccountServiceManager() {
        cacheMap = new HashMap<>(CACHE_SIZE);
    }
    
    @Override
    public Long getAmount(Integer id) throws AccountServiceException {
        if (!isIdValid(id)) {
            throw new AccountServiceException("Unacceptable id=" + id);
        }
        
        Long result = cacheMap.get(id);
        
        if (result == null) {
            //TODO
        }
        
        return result;
    }

    @Override
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        if (!isIdValid(id)) {
            throw new AccountServiceException("Unacceptable id=" + id);
        }
        
        if (!isAmountValid(value)) {
            throw new AccountServiceException("Unacceptable amount=" + value);
        }

        //TODO
    }
    
    private boolean isIdValid(Integer id) {
        return (id != null);
    }
    
    private boolean isAmountValid(Long amount) {
        return (amount != null);
    }
    
}
