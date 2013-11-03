/*
 * Author: Siarhei Skavarodkin
 * Email : komarevsky@gmail.com, admin@freebetbot.com
 */
package com.freebetbot.as.api;

/**
 * Account Service API
 * @author Siarhei Skavarodkin
 */
public interface AccountService {
    /**
     * Retrieves current balance or zero if addAmount() method was not called before for specified id
     *
     * @param id balance identifier
     * @return current balance or zero if addAmount() method was not called before for specified id
     * @throws com.freebetbot.as.api.AccountServiceException if any failure in service
     */
    public Long getAmount(Integer id) throws AccountServiceException;
    
    /**
     * Increases balance or set if addAmount() method was called first time
     *
     * @param id balance identifier
     * @param value positive or negative value, which must be added to current balance
     * @throws com.freebetbot.as.api.AccountServiceException if any failure in service
     */
    public void addAmount(Integer id, Long value) throws AccountServiceException;
}
