/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client.cmd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class ArgsParser {
    
    private ArgsParser() {}
    
    /**
     * parses args
     * @param args command line arguments to parse
     * @return Options object or null if parsing failed
     */
    public static Options parse(String[] args) {
        if (args == null || args.length != Options.OPTIONS_NUMBER) {
            System.err.println("Check number of arguments");
            return null;
        } else {
            return new ArgsParser().parseArgs(args);
        }
    }
    
    private Options parseArgs(String[] args) {
        Options options = new Options();
        
        boolean rSpecified = false;
        boolean wSpecified = false;
        boolean idSpecified = false;
        boolean urlSpecified = false;
        
        for (String s : args) {
            if (s.startsWith(Options.R_COUNT_OPTION)) {
                rSpecified = parseReaders(s, options);
                
            } else if (s.startsWith(Options.W_COUNT_OPTION)) {
                wSpecified = parseWriters(s, options);

            } else if (s.startsWith(Options.ID_LIST_OPTION)) {
                idSpecified = parseIdList(s, options);
            
            } else if (s.startsWith(Options.URL_OPTION)) {
                urlSpecified = parseUrl(s, options);
            }
        }
        
        return (rSpecified && wSpecified && idSpecified && urlSpecified)
            ? options
            : null;
    }
    
    /**
     * parses readers option and set its value to options
     * @param s the full option
     * @return true if parsing successful
     */
    private boolean parseReaders(String s, Options options) {
        boolean result;
        
        String opt = s.substring(Options.R_COUNT_OPTION.length());
        try {
            int readers = Integer.parseInt(opt);
            if (readers >= 0) {
                options.setReaders(readers);
                result = true;
            } else {
                result = false;
            }
        } catch (NumberFormatException ex) {
            System.err.println("Incorrect readers number");
            result = false;
        }
        
        return result;
    }
    
    /**
     * parses writers option and set its value to options
     * @param s the full option
     * @return true if parsing successful
     */
    private boolean parseWriters(String s, Options options) {
        boolean result;
        
        String opt = s.substring(Options.W_COUNT_OPTION.length());
        try {
            int writers = Integer.parseInt(opt);
            if (writers >= 0) {
                options.setWriters(writers);
                result = true;
            } else {
                result = false;
            }
        } catch (NumberFormatException ex) {
            System.err.println("Incorrect writers number");
            result = false;
        }
        
        return result;
    }

    /**
     * parses idList option and set its value to options
     * @param s the full option
     * @return true if parsing successful
     */
    private boolean parseIdList(String s, Options options) {
        boolean result = false;
        
        String opt = s.substring(Options.ID_LIST_OPTION.length());
        String[] ids = opt.split(",");
        if (ids.length > 0) {
            List idList = new ArrayList(ids.length);
            for (String idString : ids) {
                try {
                    int id = Integer.parseInt(idString);
                    if (id >= 0) {
                        idList.add(id);
                    }
                } catch(NumberFormatException ex) {
                    System.err.println("Invalid key=" + idString);
                }
            }

            if (!idList.isEmpty()) {
                options.setIdList(idList);
                result = true;
            }
        }
        
        return result;
    }
    
    /**
     * parses url option and set its value to options
     * @param s the full option
     * @return true if parsing successful
     */
    private boolean parseUrl(String s, Options options) {
        boolean result = false;
        
        String opt = s.substring(Options.URL_OPTION.length());
        if (!opt.isEmpty()) {
            options.setServiceUrl(opt);
            result = true;
        } else {
            System.err.println("Url is empty");
        }
        
        return result;
    }    

}
