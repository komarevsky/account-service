/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.support;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Provides methods to retrieve hibernate session
 * @author Siarhei Skavarodkin
 */
public class HibernateHelper {
    
    private static SessionFactory factory;
    
    public static synchronized Session getSession() {
        if (factory == null) {
            factory = configureSessionFactory();
        }
        return factory.openSession();
    }
    
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
