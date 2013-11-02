/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client.cmd;

import java.util.List;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class Options {

    public static final int OPTIONS_NUMBER=4;    
    public static final String R_COUNT_OPTION="--rCount=";
    public static final String W_COUNT_OPTION="--wCount=";
    public static final String ID_LIST_OPTION="--idList=";
    public static final String URL_OPTION="--url=";

    // number threads calling getAmount
    private int readers;
    
    // number threads calling addAmount
    private int writers;
    
    // list of IDs
    private List<Integer> idList;
    
    // url to AccountService instance
    private String serviceUrl;

    public int getReaders() {
        return readers;
    }

    public void setReaders(int readers) {
        this.readers = readers;
    }

    public int getWriters() {
        return writers;
    }

    public void setWriters(int writers) {
        this.writers = writers;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

}
