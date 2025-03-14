package com.cloud.boot.common.core.constant;

/**
 * @author lhd
 */
public interface SaTokenConstant {

    String SA_TOKEN_TOKEN_NAME = "Authorization";
    String SA_TOKEN_TOKEN_STYLE = "simple-uuid";
    long SA_TOKEN_TIME_OUT = 24*3600;
    long SA_TOKEN_ACTIVE_TIMEOUT = -1;
    boolean SA_TOKEN_IS_CONCURRENT = true;
    boolean SA_TOKEN_IS_SHARE = true;
    boolean SA_TOKEN_IS_LOG = true;
    boolean SA_TOKEN_IS_READ_COOKIE = false;
    boolean SA_TOKEN_IS_READ_BODY = false;
    boolean SA_TOKEN_IS_READ_HEADER = true;
}
