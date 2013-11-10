/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.account;

import com.freebetbot.as.api.AccountServiceException;
import com.freebetbot.as.service.support.HibernateHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Account DB Helper
 * @author Siarhei Skavarodkin
 */
class AccountServiceDb {
    
    /**
     * returns amount for specified id
     * @return amount or null if non-existing id
     * @throws AccountServiceException if any issue
     */
    public Long getAmountById(Integer id) throws AccountServiceException {
        Long result = null;
        
        Session session = null;
        try {
            session = HibernateHelper.getSession();
            Account account = (Account) session.get(Account.class, id);
            if (account != null) {
                result = account.getAmount();
            }
            session.close();
        } catch(HibernateException ex) {
            if (session != null) {
                session.close();
            }
            throw new AccountServiceException(ex);
        }
        
        return result;
    }
    
    /**
     * sets amount for specified id
     * @param id
     * @param amount
     * @throws AccountServiceException if any issue occurs
     */
    public void setAmountById(Integer id, Long amount) throws AccountServiceException {
        Account account = new Account(id, amount);
        
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateHelper.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(account);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (session != null) {
                session.close();
            }
            throw new AccountServiceException(ex);
        }
    }
}
