package com.cloud.boot.common.feign;

import com.cloud.boot.common.feign.aop.InnerAop;
import com.cloud.boot.common.feign.interceptor.FeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 * <p>
 * Feign 统一配置
 */
@Configuration(proxyBeanMethods = false)
public class FeignAutoConfiguration {

	@Bean
	public InnerAop innerAop() {
		return new InnerAop();
	}

	@Bean
	public FeignInterceptor feignInterceptor() {
		return new FeignInterceptor();
	}
}
