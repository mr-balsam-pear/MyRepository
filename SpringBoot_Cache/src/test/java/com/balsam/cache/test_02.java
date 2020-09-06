package com.balsam.cache;

import com.balsam.cache.entity.Department;
import com.balsam.cache.mapper.IDepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class test_02 {

    @Autowired
    private IDepartmentMapper departmentMapper;

    @Autowired
    RedisTemplate redisTemplate;//操作k-v都是对象的

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Autowired
    RedisTemplate<Object,Department> redisTemplateSer;//自定义序列化

    @Test
    void redisTest() {
        stringRedisTemplate.opsForValue().set("redis_02", "balsam");
    }

    @Test
    void redisTest2() {
        String redis_02 = stringRedisTemplate.opsForValue().get("redis_02");
        System.out.println(redis_02);
    }

    @Test
    void redisForObject() {
        //默认如果保存对象，使用jdk序列号机制，序列化后数据保存到redis中
        Department department = departmentMapper.findById(1l);
        //将数据以json保存在redis中
        redisTemplate.opsForValue().set("deaprtment", department);
    }

    @Test
    void redisForObject2() {
        //默认如果保存对象，使用jdk序列号机制，序列化后数据保存到redis中
        Department department = departmentMapper.findById(1l);
        //将数据以json保存在redis中
        redisTemplateSer.opsForValue().set("deaprtment2", department);
    }

}
