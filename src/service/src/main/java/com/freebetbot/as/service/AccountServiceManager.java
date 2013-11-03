/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service;

import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import java.util.HashMap;

/**
 * manager for operations with account
 * @author Siarhei Skavarodkin
 */
class AccountServiceManager implements AccountService {

    private static final int CACHE_SIZE = 1000;
    
    // <id, amount>
    private final HashMap<Integer, Long> cacheMap;
    private final AccountServiceDb dbHelper;
    
    public AccountServiceManager() {
        cacheMap = new HashMap<>(CACHE_SIZE);
        dbHelper = new AccountServiceDb();
    }
    
    @Override
    public Long getAmount(Integer id) throws AccountServiceException {
        if (!isIdValid(id)) {
            throw new AccountServiceException("Unacceptable id=" + id);
        }
        
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
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        if (!isIdValid(id)) {
            throw new AccountServiceException("Unacceptable id=" + id);
        }
        
        if (!isAmountValid(value)) {
            throw new AccountServiceException("Unacceptable amount=" + value);
        }

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
    
    private boolean isIdValid(Integer id) {
        return (id != null);
    }
    
    private boolean isAmountValid(Long amount) {
        return (amount != null);
    }
    
}
