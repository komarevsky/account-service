/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.account;

import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.api.AccountServiceException;
import com.freebetbot.as.service.statistics.StatisticsManager;
import java.util.HashMap;

/**
 * manager for operations with account
 * @author Siarhei Skavarodkin
 */
public class AccountServiceManager implements AccountService {

    private static final int CACHE_SIZE = 1000;
    
    private final StatisticsManager statisticsManager;
    // <id, amount>
    private final HashMap<Integer, Long> cacheMap;
    private final AccountServiceDb dbHelper;
    
    public AccountServiceManager(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
        cacheMap = new HashMap<>(CACHE_SIZE);
        dbHelper = new AccountServiceDb();
    }
    
    @Override
    public Long getAmount(Integer id) throws AccountServiceException {
        statisticsManager.incGetAmountCounter();
        
        if (!isIdValid(id)) {
            statisticsManager.decGetAmountCounter();
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
        
        statisticsManager.decGetAmountCounter();
        return result;
    }

    @Override
    public void addAmount(Integer id, Long value) throws AccountServiceException {
        statisticsManager.incAddAmountCounter();
        if (!isIdValid(id)) {
            statisticsManager.decAddAmountCounter();
            throw new AccountServiceException("Unacceptable id=" + id);
        }
        
        if (!isAmountValid(value)) {
            statisticsManager.decAddAmountCounter();
            throw new AccountServiceException("Unacceptable amount=" + value);
        }

        Long currentAmount = getAmount(id);
        Long newAmount = currentAmount + value;
        dbHelper.setAmountById(id, newAmount);
        cacheData(id, newAmount);
        
        statisticsManager.decAddAmountCounter();
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
