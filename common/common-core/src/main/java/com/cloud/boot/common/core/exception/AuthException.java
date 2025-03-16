package com.cloud.boot.common.core.exception;

/**
 * 认证异常
 * @author lhd
 */
public class AuthException extends RuntimeException {

    public AuthException(String msg) {
        this(msg, null);
    }

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }
}
