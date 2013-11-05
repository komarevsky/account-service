/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.freebetbot.as.api.AccountService;
import com.freebetbot.as.client.cmd.ArgsParser;
import com.freebetbot.as.client.cmd.Help;
import com.freebetbot.as.client.cmd.Options;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;

/**
 * Entry point to application
 * @author Siarhei Skavarodkin
 */
public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class);
    
    public static void main(String[] args) {        
        Options options = ArgsParser.parse(args);
        if (options == null) {
            System.out.println(Help.getUsage());
            System.exit(1);
        }
        
        AccountService service = getService(options.getServiceUrl());
        if (service == null) {
            LOGGER.fatal("Account Service is unavailable");
            System.exit(1);
        }
        
        //run all readers
        for (int i=0; i<options.getReaders(); ++i) {
            ReadTester r = new ReadTester(service, options.getIdList(), 
                    "ReadThread" + i);
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.start();
        }
        
        //run all writers
        for (int i=0; i<options.getWriters(); ++i) {
            WriteTester w = new WriteTester(service, options.getIdList(), 
                    "WriteTester" + i);
            Thread t = new Thread(w);
            t.setDaemon(true);
            t.start();
        }
        
        //wait until user press any button to finish program
        System.out.println("Press <Enter> to exit client");
        readKey();        
    }
    
    private static AccountService getService(String url) {
        AccountService service;
        
        try {
            HessianProxyFactory factory = new HessianProxyFactory();
            service = (AccountService) factory.create(AccountService.class, url);
        } catch(MalformedURLException ex) {
            LOGGER.error("can not connect to service", ex);
            service = null;
        }
        
        return service;
    }
    
    private static void readKey() {
        try {
            System.in.read();
        } catch (IOException ex) {}
    }
}
