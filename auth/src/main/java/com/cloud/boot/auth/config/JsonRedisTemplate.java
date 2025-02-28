package com.cloud.boot.auth.config;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.cloud.boot.common.core.util.JacksonUtil;
import com.cloud.boot.common.core.util.LocalDateTimeModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author lhd
 */
@Component
public class JsonRedisTemplate extends RedisTemplate<String, Object> {

    public JsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        super.setConnectionFactory(redisConnectionFactory);

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        serializer.configure(JacksonUtil::configObjectMapper);
        
        super.setKeySerializer(StringRedisSerializer.UTF_8);
        super.setValueSerializer(serializer);
        super.setHashKeySerializer(StringRedisSerializer.UTF_8);
        super.setHashValueSerializer(serializer);
    }
}