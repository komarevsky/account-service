/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.client.cmd;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class Help {
    
    public static String getUsage() {
        String s = "Usage:\n" + Options.R_COUNT_OPTION + "<countOfReaders> "
                + Options.W_COUNT_OPTION + "<countOfWriters> "
                + Options.ID_LIST_OPTION + "<IdsSeparatedByCommas> "
                + Options.URL_OPTION + "<urlToService>\n"
                + "Example:\n" + Options.R_COUNT_OPTION + "10 "
                + Options.W_COUNT_OPTION + "12 "
                + Options.ID_LIST_OPTION + "1,2,3,4,5,6 "
                + Options.URL_OPTION + "http://localhost:8084/account-service";
        return s;
    }
}
