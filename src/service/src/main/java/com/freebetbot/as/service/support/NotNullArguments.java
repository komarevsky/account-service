/*
 * Author: Siarhei Skavarodkin
 * email: komarevsky@gmail.com, admin@freebetbot.com
 */

package com.freebetbot.as.service.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All arguments given to method annotated by NotNullArguments
 * will be checked to be not null. If any of parameters is null, then
 * <code>AccountServiceException</code> will be thrown.
 * Check is done via Aspect mechanism
 * @see com.freebetbot.as.api.AccountServiceException
 * @see com.freebetbot.as.service.support.NotNullArgsAspect
 * @author Siarhei Skavarodkin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotNullArguments {
    
}
