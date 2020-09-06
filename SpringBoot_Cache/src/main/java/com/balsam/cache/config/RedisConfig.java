package com.balsam.cache.config;

import com.balsam.cache.entity.Department;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisConfig {
    /**
     * 自定义序列化对象存入redis
     *
     * @return
     * @Param: null
     */
//    @Bean
//    public RedisTemplate<Object, Department> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
//        RedisTemplate<Object, Department> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<>(Department.class);
//        template.setDefaultSerializer(serializer);
//        return template;
//    }

    /**
     * create by: balsam
     * description: TODO
     * springboot2.0x 以后 不需要配置redisTemplate 和manager，它另外导入了lutto缓存
     * 所以只需要配置一个cachemanger 不用给每一个pojo都配一个缓存工具了
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))
                        .disableCachingNullValues()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
                                GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
    }

}
