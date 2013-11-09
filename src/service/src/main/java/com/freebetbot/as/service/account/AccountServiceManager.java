/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.account;

import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import com.freebetbot.as.service.support.NotNullArguments;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * manager for operations with account
 * @author Siarhei Skavarodkin
 */
public class AccountServiceManager implements AccountService {

    private static final int CACHE_SIZE = 1000;
    
    // <id, amount>
    private final Map<Integer, Long> cacheMap;
    private final AccountServiceDb dbHelper;
    
    public AccountServiceManager() {
        cacheMap = new ConcurrentHashMap<>(CACHE_SIZE);
        dbHelper = new AccountServiceDb();
    }
    
    @Override
    @NotNullArguments
    public Long getAmount(Integer id) throws AccountServiceException {
        Long result = cacheMap.get(id);

        if (result == null) { //cache does not contain data
            result = dbHelper.getAmountById(id);
            if (result == null) { //database does not contain data
                result = 0L;
                dbHelper.setAmountById(id, result);
            } else {
                cacheData(id, result);
            }
        }
        return result;
    }

    @Override
    @NotNullArguments
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        Long currentAmount = getAmount(id);
        Long newAmount = currentAmount + value;
        dbHelper.setAmountById(id, newAmount);
        cacheData(id, newAmount);
    }
    
    /**
     * stores data in cache
     * @param id
     * @param amount 
     */
    private void cacheData(Integer id, Long amount) {
        //TODO: optimization or framework
        if (cacheMap.size() >= CACHE_SIZE) {
            cacheMap.clear();            
        }
        cacheMap.put(id, amount);
    }
    
}
