package com.balsam.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * —、搭建基本环境
 *      1、导入数据库文件创建出department和employee表
 *      2、创建javaBean封装数据
 *      3、整合MyBatis操作数据库
 *          1.配置数据源信息
 *          2.使用注解版的MyBatis;
 *      1) .@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *  步骤:
 *      1、开启基于注解的缓存@EnableCaching
 *      2、标注缓存注解即可
 *          @Cacheable
 *          @cacheEvict
 *          @CachePut
 *
 *
 *   三、整合redis作为缓存
 *          1、安装redis:使用docker;
 *          2、引Aredis的starter
 *          3、配置redis
 *          4、测试缓存
 * 原理:CacheManager===Cache缓存组件来实际给缓存中存取数据
 *      1)、引入redis的starter，容器中保存的是 RedisCacheManager;
 *          Redis是一个开源（BSo许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
 *      2)、RedisCacheManager帮我们创建RedisCache来作为缓存组件，RedisCache通过操作redis缓存数据
 */
@SpringBootApplication
@MapperScan(value = "com.balsam.cache.mapper")
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
