package com.cloud.boot.common.log;

import com.cloud.boot.common.log.aspect.OperateLogAspect;
import com.cloud.boot.user.feign.OperationLogFeignClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    @Bean
    @ConditionalOnBean(OperationLogFeignClient.class)
    public OperateLogAspect operateLogAspect(OperationLogFeignClient operationLogFeignClient) {
        return new OperateLogAspect(operationLogFeignClient);
    }
}
