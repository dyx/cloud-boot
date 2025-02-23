package com.cloud.boot.common.feign.handler;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.core.util.R;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.DataTruncation;
import java.sql.SQLException;

/**
 * @author lhd
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final int SQL_ERROR_CODE_NO_DEFAULT_VALUE = 1364;

    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> handle(MethodArgumentNotValidException e) {

        String msgStr = "";
        StringBuilder msgBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            msgBuilder.append(error.getField());
            msgBuilder.append(error.getDefaultMessage());
            msgBuilder.append(";");
        }

        if (!msgBuilder.isEmpty()) {
            msgStr = msgBuilder.substring(0, msgBuilder.length() - 1);
        }

        return R.fail(GlobalErrorCodeEnum.REST_PARAM_VALIDATE_ERROR.getCode(), msgStr);
    }

    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<?> handle(ConstraintViolationException e) {
        StringBuilder msgBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            msgBuilder.append(violation.getPropertyPath());
            msgBuilder.append(violation.getMessage());
            msgBuilder.append(";");
        }
        if (log.isDebugEnabled()) {
            log.debug(msgBuilder.toString());
        }
        return R.fail(GlobalErrorCodeEnum.REST_PARAM_VALIDATE_ERROR.getCode(), msgBuilder.toString());
    }

    /**
     * 请求方式错误
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> handle(HttpRequestMethodNotSupportedException e) {
        return R.fail(GlobalErrorCodeEnum.REST_REQUEST_METHOD_ERROR.getCode(), String.format("%s，支持的请求方式为%s",
                GlobalErrorCodeEnum.REST_REQUEST_METHOD_ERROR.getMsg(), e.getSupportedHttpMethods() != null ? e.getSupportedHttpMethods().toString() : ""));
    }

    /**
     * 参数缺失异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R<?> handle(MissingServletRequestParameterException e) {
        return R.fail(GlobalErrorCodeEnum.REST_PARAM_VALIDATE_ERROR.getCode(), String.format("缺失参数【%s】", e.getParameterName()));
    }

    /**
     * 参数转换异常
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<?> handle(HttpMessageNotReadableException e) {

        if (e.getCause() instanceof InvalidFormatException exception) {
            return R.fail(GlobalErrorCodeEnum.REST_PARAM_VALIDATE_ERROR.getCode(),
                    String.format("值【%s】转换为类型【%s】失败", exception.getValue(), exception.getTargetType().getName()));
        }

        log.error("请求参数转换异常，原因：{}", e.getMessage());

        return R.fail(GlobalErrorCodeEnum.REST_PARAM_VALIDATE_ERROR.getCode(), String.format("请求参数转换异常，%s", e.getMessage()));
    }

    /**
     * 媒体类型异常
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R<?> handle(HttpMediaTypeNotSupportedException e) {

        return R.fail(GlobalErrorCodeEnum.REST_MEDIA_TYPE_ERROR.getCode(), String.format("%s，%s", GlobalErrorCodeEnum.REST_REQUEST_METHOD_ERROR.getMsg(), e.getMessage()));
    }

    /**
     * 访问异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public R<?> handle(AccessDeniedException e) {

        return R.fail(GlobalErrorCodeEnum.REST_ACCESS_DENIED.getCode(), e.getMessage());
    }

    /**
     * 数据库-数据完整性异常
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public R<?> handle(DataIntegrityViolationException e) {

        log.error("数据库异常，原因：{}", e.getMessage());

        if (e.getCause() instanceof DataTruncation) {
            return R.fail(GlobalErrorCodeEnum.DB_DATA_TOO_LONG);
        }
        if (e.getCause() instanceof SQLException exception) {
            if (exception.getErrorCode() == SQL_ERROR_CODE_NO_DEFAULT_VALUE) {
                return R.fail(GlobalErrorCodeEnum.DB_DATA_NO_DEFAULT_VALUE);
            }
        }

        return R.fail(GlobalErrorCodeEnum.DB_DATA_INTEGRITY_VIOLATION.getCode(), e.getMessage());
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public R<?> handle(BizException e) {
        log.error(e.getMessage(), e);
        return R.fail(e.getCode(), e.getMessage());
    }

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<?> handle(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail(GlobalErrorCodeEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
    }
}
