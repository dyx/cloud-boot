package com.cloud.boot.common.feign.annotation;

import java.lang.annotation.*;

/**
 * @author lhd
 * 微服务内部调用，不对外暴露接口
 * <p></p>
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

}
