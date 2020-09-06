package com.balsam.cache;

import com.balsam.cache.entity.Department;
import com.balsam.cache.mapper.IDepartmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApplicationTests {
    @Autowired
    private IDepartmentMapper departmentMapper;

    @Autowired
    RedisTemplate redisTemplate;//操作k-v都是对象的

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Test
    void contextLoads() {
        Department byId = departmentMapper.findById(1l);
        System.out.println(byId);

    }

    @Test
    void redisTest() {
        stringRedisTemplate.opsForValue().set("redis_02","balsam");
    }

    @Test
    void redisTest2() {
        String redis_02 = stringRedisTemplate.opsForValue().get("redis_02");
        System.out.println(redis_02);
    }

}
