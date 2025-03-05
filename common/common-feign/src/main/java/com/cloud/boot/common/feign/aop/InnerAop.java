package com.cloud.boot.common.feign.aop;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.feign.annotation.Inner;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author lhd
 */
@Slf4j
@Aspect
public class InnerAop {

    @Before("@annotation(innerAnnotation)")
    public void before(JoinPoint joinPoint, Inner innerAnnotation) {

        HttpServletRequest request = getRequest();
        if (request != null) {
            String innerValue = request.getHeader(CommonConstant.REQUEST_HEADER_INNER);
            if (innerAnnotation != null && !"true".equals(innerValue)) {
                throw new BizException(GlobalErrorCodeEnum.REST_ACCESS_DENIED);
            }
        }
    }

    public static HttpServletRequest getRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        catch (Exception ignored) {}
        return null;
    }
}
