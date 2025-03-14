package com.cloud.boot.common.config.aop;

import com.cloud.boot.common.core.jackson.JacksonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
public class HttpLogAop {

    private final ThreadLocal<Long> initRequestTime = new ThreadLocal<>();

    @Value("${custom.http.log.enable:true}")
    private Boolean enabled;

    @Pointcut("execution(public * com.cloud.boot..controller.*Controller.*(..))")
    private void point() {
    }

    @Before("point()")
    public void before(JoinPoint joinPoint) {

        if (enabled) {
            try {
                initRequestTime.set(System.currentTimeMillis());

                HttpServletRequest request = getRequest();

                log.info("******************** HTTP Request Start ********************");
                log.info("url：{}", request.getRequestURI());
                log.info("method：{}", request.getMethod());
                log.info("classMethod：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
                log.info("params：{}", JacksonUtil.toJsonStr(joinPoint.getArgs()));
                log.info("******************** HTTP Request End ********************");

            } catch (Exception e) {
                log.error("记录Http请求日志出错", e);
            }
        }
    }

    @AfterReturning(returning = "obj", pointcut = "point()")
    public void afterReturning(Object obj) {

        if (enabled) {
            try {
                HttpServletRequest request = getRequest();
                log.info("******************** HTTP Response Start ********************");
                log.info("url：{}", request.getRequestURI());
                log.info("method：{}", request.getMethod());
                log.info("body：{}", JacksonUtil.toJsonStr(obj));
                log.info("耗时：{}ms", (System.currentTimeMillis() - initRequestTime.get()));
                log.info("******************** HTTP Response End ********************");
                // 防止线程复用造成的数据混乱
                initRequestTime.remove();
            } catch (Exception e) {
                log.error("记录Http响应日志出错", e);
            }
        }
    }

    public static HttpServletRequest getRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        catch (Exception e) {}
        return null;
    }
}
