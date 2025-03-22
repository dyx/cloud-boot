package com.cloud.boot.common.config.aspect;

import com.cloud.boot.common.core.jackson.JacksonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author lhd
 * <p></p>
 * http请求日志记录
 */
@Slf4j
@Aspect
public class HttpLogAspect {

    @Value("${custom.http.log.enable:true}")
    private Boolean enabled;

    @Around("execution(public * com.cloud.boot..controller.*Controller.*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        if (enabled) {

            long startTime = System.currentTimeMillis();
            result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            HttpServletRequest request = getRequest();
            String method = request != null ? request.getMethod() : "";
            String requestUrl = request != null ? request.getRequestURL().toString() : "";
            String queryString = request != null ? request.getQueryString() : "";
            String fullUrl = requestUrl + (queryString != null ? "?" + queryString : "");

            log.info("******************** HTTP Log Start ********************");
            log.info("{} {}", method, fullUrl);
            log.info("classMethod：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            log.info("params：{}", JacksonUtil.toJsonStr(joinPoint.getArgs()));
            log.info("body：{}", JacksonUtil.toJsonStr(result));
            log.info("time：{}ms", duration);
            log.info("******************** HTTP Log End ********************");
        }
        else {
            result = joinPoint.proceed();
        }

        return result;
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
