package com.cloud.boot.common.translation;

import com.cloud.boot.common.translation.aspect.TranslationAspect;
import com.cloud.boot.common.translation.feign.UserServiceFeignClient;
import com.cloud.boot.common.translation.translator.Translator;
import com.cloud.boot.common.translation.translator.impl.DictTranslator;
import com.cloud.boot.common.translation.translator.impl.UserTranslator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author lhd
 */
@Configuration(proxyBeanMethods = false)
public class TranslationAutoConfiguration {

    @Bean
    public TranslationAspect translationAspect(List<Translator<?>> translators) {
        return new TranslationAspect(translators);
    }

    @Bean
    @ConditionalOnBean(UserServiceFeignClient.class)
    public Translator<String> dictTranslator(UserServiceFeignClient userServiceFeignClient) {
        return new DictTranslator(userServiceFeignClient);
    }

    @Bean
    @ConditionalOnBean(UserServiceFeignClient.class)
    public Translator<Long> userTranslator(UserServiceFeignClient userServiceFeignClient) {
        return new UserTranslator(userServiceFeignClient);
    }
}
