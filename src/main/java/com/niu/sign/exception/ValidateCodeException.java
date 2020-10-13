package com.niu.sign.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @author Zian.Niu
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}