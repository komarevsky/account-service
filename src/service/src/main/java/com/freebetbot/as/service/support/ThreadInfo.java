/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.support;

/**
 * Provides info about thread
 * @author Siarhei Skavarodkin
 */
public class ThreadInfo {
    
    public String getThreadInfo() {
        StringBuilder sb = new StringBuilder();
        
        Thread t = Thread.currentThread();
        sb.append("id=").append(t.getId())
                .append("\nname=").append(t.getName())
                .append("\nstackTrace=\n")
                .append(stackTraceToString(t.getStackTrace()));
        
        return sb.toString();
    }
    
    private String stackTraceToString(StackTraceElement[] stack) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement el : stack) {
            sb.append(el.getClassName()).append(".").append(el.getMethodName())
                    .append(":").append(el.getLineNumber()).append("\n");
        }
        return sb.toString();
    }
}
