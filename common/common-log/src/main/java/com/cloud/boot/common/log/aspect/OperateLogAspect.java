package com.cloud.boot.common.log.aspect;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.cloud.boot.common.core.constant.enums.OperationLogStatusEnum;
import com.cloud.boot.common.core.jackson.JacksonUtil;
import com.cloud.boot.common.log.annontaion.OperationLog;
import com.cloud.boot.common.resource.server.util.UserUtil;
import com.cloud.boot.user.feign.OperationLogFeignClient;
import com.cloud.boot.user.model.dto.SaveOperationLogDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lhd
 */
@Aspect
@RequiredArgsConstructor
public class OperateLogAspect {

    private final OperationLogFeignClient operationLogFeignClient;

    @Around("@annotation(operationLogAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, OperationLog operationLogAnnotation) throws Throwable {

        SaveOperationLogDTO dto = new SaveOperationLogDTO();
        long startTime = System.currentTimeMillis();
        try {

            HttpServletRequest request = ((ServletRequestAttributes) Objects
                    .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            dto.setModule(operationLogAnnotation.module());
            dto.setType(operationLogAnnotation.type());
            dto.setOperationTime(LocalDateTime.now());
            dto.setOperationIp(JakartaServletUtil.getClientIP(request));
            dto.setOperationUserAgent(request.getHeader("User-Agent"));
            dto.setOperatorId(UserUtil.getUserId());

            Object result = joinPoint.proceed();

            dto.setMethod(method.getDeclaringClass().getName() + "#" + method.getName());
            dto.setParams(JacksonUtil.toJsonStr(joinPoint.getArgs()));
            dto.setResult(JacksonUtil.toJsonStr(result));
            dto.setStatus(OperationLogStatusEnum.SUCCESS.getValue());

            return result;
        } catch (Exception e) {

            dto.setStatus(OperationLogStatusEnum.FAILURE.getValue());
            dto.setErrorMsg(e.getMessage());
            throw e;
        } finally {

            dto.setElapsedTime(System.currentTimeMillis() - startTime);
            asyncSaveLog(dto);
        }
    }

    @Async
    public void asyncSaveLog(SaveOperationLogDTO dto) {
        operationLogFeignClient.saveOperationLog(dto);
    }
}