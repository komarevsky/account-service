/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */
package com.freebetbot.as.service.support;

import com.freebetbot.as.api.AccountServiceException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * This aspect checks all parameters given to method annotated as
 * <code>@NotNullArguments</code>. All arguments must be not null, otherwise
 * <code>AccountServiceException</code> is thrown
 *
 * @see com.freebetbot.as.api.AccountServiceException
 * @see com.freebetbot.as.service.support.NotNullArguments
 * @author Siarhei Skavarodkin
 */
@Aspect
public class NotNullArgsAspect {

    private static final Logger LOGGER = Logger.getLogger(NotNullArgsAspect.class);

    @Pointcut("@annotation(com.freebetbot.as.service.support.NotNullArguments)")
    public void notNullArgumentsMethod() {
    }

    @Around("notNullArgumentsMethod()")
    public Object notNullArgumentsCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        
        for (Object o : methodArgs) {
            if (o == null) {
                throw new AccountServiceException("method " 
                        + methodName + " called with arguments containing null: " 
                        + argsToString(methodArgs));
            }
        }

        try {
            if (LOGGER.isTraceEnabled()) {
                StringBuilder sb = new StringBuilder("call method ");
                        sb.append(methodName).append(" with args: ")
                                .append(argsToString(methodArgs));
                LOGGER.trace(sb);
            }
            return thisJoinPoint.proceed(methodArgs);
        } catch (Throwable ex) {
            LOGGER.error("Exception during execution of " + methodName, ex);
            if (ex instanceof AccountServiceException) {
                throw (AccountServiceException) ex;
            } else {
                throw new AccountServiceException(ex.getMessage(), ex);
            }
        }
    }
    
    private String argsToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            sb.append("param").append(i).append("=").append(args[i]).append("; ");
        }
        return sb.toString();
    }
}
