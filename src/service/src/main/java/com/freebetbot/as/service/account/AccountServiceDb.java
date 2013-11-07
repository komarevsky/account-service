/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.account;

import com.freebetbot.as.api.AccountServiceException;
import javax.annotation.Resource;

/**
 * DB Helper
 * @author Siarhei Skavarodkin
 */
class AccountServiceDb {

    @Resource(lookup = "java:comp/env/jdbc/AccountServiceDS")
    private javax.sql.DataSource ds;
    
    /**
     * returns amount for specified id
     * @return amount or null if non-existing id
     * @throws AccountServiceException if any issue
     */
    public Long getAmountById(Integer id) throws AccountServiceException {
        // TODO get amount
        return 0L;
    }
    
    /**
     * sets amount for specified id
     * @param id
     * @param amount
     * @throws AccountServiceException if any issue occurs
     */
    public void setAmountById(Integer id, Long amount) throws AccountServiceException {
        //TODO set amount
    }
}