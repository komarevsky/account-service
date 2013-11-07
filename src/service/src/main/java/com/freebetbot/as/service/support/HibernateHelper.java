/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.support;

import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Provides methods to retrieve hibernate session
 * @author Администратор
 */
public class HibernateHelper {
    
    @Resource(lookup = "java:comp/env/jdbc/AccountServiceDS")
    private static javax.sql.DataSource ds;
    
    private static final String DS_JNDI_NAME = "java:comp/env/jdbc/AccountServiceDS";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.DerbyDialect";
    //"org.hibernate.dialect.MySQLDialect"
    private static SessionFactory factory;
    
    public static Session getSession() {
        if (factory == null) {
            factory = configureSessionFactory();
        }
        return factory.openSession();
    }
    
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.datasource", DS_JNDI_NAME)
                .setProperty("hibernate.dialect", HIBERNATE_DIALECT)
                .addAnnotatedClass(com.freebetbot.as.service.account.Account.class)
                ;
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
