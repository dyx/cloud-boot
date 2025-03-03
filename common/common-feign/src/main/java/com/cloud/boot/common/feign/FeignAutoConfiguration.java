package com.cloud.boot.common.feign;

import com.cloud.boot.common.feign.aop.HttpLogAop;
import com.cloud.boot.common.feign.interceptor.FeignInterceptor;
import com.cloud.boot.common.feign.sentinel.CustomBlockExceptionHandler;
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
	public HttpLogAop httpLogAop() {
		return new HttpLogAop();
	}

	@Bean
	public CustomBlockExceptionHandler blockHandler() {
		return new CustomBlockExceptionHandler();
	}

	@Bean
	public FeignInterceptor feignInterceptor() {
		return new FeignInterceptor();
	}
}
