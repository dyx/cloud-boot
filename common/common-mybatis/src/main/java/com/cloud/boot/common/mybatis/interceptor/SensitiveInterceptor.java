package com.cloud.boot.common.mybatis.interceptor;

import com.cloud.boot.common.mybatis.annotation.Sensitive;
import com.cloud.boot.common.mybatis.util.SensitiveUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * @author lhd
 */
@Intercepts({
    @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class SensitiveInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (result instanceof List<?> list) {
            for (Object item : list) {
                processSensitiveFields(item);
            }
        } else {
            processSensitiveFields(result);
        }
        return result;
    }

    private void processSensitiveFields(Object item) {
        if (item == null) return;
        Class<?> clazz = item.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            Sensitive sensitive = field.getAnnotation(Sensitive.class);
            if (sensitive != null && field.getType() == String.class) {
                try {
                    field.setAccessible(true);
                    String originalValue = (String) field.get(item);
                    if (originalValue != null) {
                        String desensitizedValue = SensitiveUtils.desensitize(originalValue, sensitive.strategy());
                        field.set(item, desensitizedValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("脱敏处理失败", e);
                }
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}
}