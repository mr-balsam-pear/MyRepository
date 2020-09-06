package com.balsam.cache.service.impl;

import com.balsam.cache.entity.Department;
import com.balsam.cache.mapper.IDepartmentMapper;
import com.balsam.cache.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
/**
 * create by: balsam
 * —、搭建基本环境
 *      1、导入数据库文件创建出department和employee表
 *      2、创建javaBear封装数据
 *      3、整合MyBatis操作数据库
 *          1.配置数据源信息
 *          2.使用注解版的MyBatis;
 *              1)、@MapperScar指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *  步骤:
 *      1、开启基于注解的缓存@EnabLeCaching
 *      2、标注缓存注解即可
 *           ocacheabLe
 *           @cacheEvict
 *           @cachePut
 * 默认使用的是ConcurrentNapCacheManager==ConcurrentMapCache，将数据保存在ConcurrentNap<Object>,
 * 开发中使用缓存中间件;redis、 memcached、 ehcache;
 *
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentMapper departmentMapper;
/**
 * create by: balsam
 * 原理:
 * 1、自动配置类;CacheAutoConfiguration
 * 2、缓存的配置类
 * org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguratior
 * org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
 * org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
 * org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
 * 3、哪个配置类默认生效:SimpleCacheConfiguration;
 * 4、给容器中注册了一个cacheManager:ConcurrentMapCacheMNanager
 * 5、可以获取和创建ConcurrentMapCache类型的缓存组件，他的作用将数据保存在ConcurrentMap中;
 *
 * 运行流程:
 * Cacheable:
 *      1、方法运行之前，先去查询cache(缓存组件），按照cacheNames指定的名字获取;
 *          (CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。
 *      2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数;
 *          key是按照某种策略生成的，默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key;
 *          SimpleKeyGenerator生成key的默认策略;
 */

    /**
     * 将方法的运行结果进行缓存﹔以后再要相同的数据，直接从缓存中获取，不用调用方法﹔
     * CacheManager管理多个Cache组件的，对缓存的真正CRUb操作在Cache组件中，每一个缓存组件有自己唯—一个名字;
     * 几个属性:
     * cacheNames/value:指定缓存组件的名字;
     * key:缓存数据使用的key;可以用它来指定。默认是使用方法参数的值W1-方法的返回值
     * 编写SpEL;#id;参数id的值#a#p#root.args[e]
     * keyGenerator: key的生成器﹔可以自己指定key的生成器的组件id
     * key/ keyGenerator:二选—使用
     * cacheManager:指定缓存管理器;或者cacheResolver指定获取解析器
     * condition:指定符合条件的情况下才缓存;
     * , condition = "#id>01
     * unlLess:否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
     * unLess ="#result == null"
     * sync:是否使用异步模式
     * <p>
     * 运行流程:
     * ecacheabLe:
     * 1、方法运行之前，先去查询cache(缓存组件），按照cacheNames指定的名字获取;
     * (CacheNanager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。
     * 2、去cache中查找缓存的内容，使用一个key，默认就是方法的参数;
     * key是按照某种策略生成的，默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
     * SimpleKeyGenerator生成key的某人策略：
     * 如果没有参数，key=new SimpleKEy();
     * 如果有一个参数，key=参数的值；
     * 如果有多个参数，key=new simplekey（params）；
     * <p>
     * 3、没有查到缓存就调用目标方法﹔
     * 4、将目标方法返回的结果，故进缓存中
     *
     * @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为hay去查询缓存，l 如果没有运行方法就将结果放入缓存，以后再来调用数据就可以直接取
     * <p>
     * 核心：
     * 1），使用CacheManager
     */

    @Cacheable(value = {"dep"}, key = "#id")
    public Department findById(Long id) {
        System.out.println("查询" + id + "号部门");
        return departmentMapper.findById(id);
    }

    /*
     *@CachePut:即调用方法，又更新缓存
     * 修改了数据库的数据，同时更新了缓存
     * 运行实际：
     *         先调用目标方法
     *          再将运行结果加入缓存
     * 测试步骤:
     * 1、查询1号员工;查到的结果会放在缓存中;
     *      key: value: lastName:张三
     * 2、以后查询还是之前的结果
     * 3、更新1号员工;.lastName : zhangsan; gender:o】
     *      将方法的返回值也放进缓存了;
     *      key:传入的employee对象值:返回的employee对象;
     * 4、查询1号员工?
     *      应该是更新后的员工;
     *          key="#employee.id":使用传入参数的id；
     *          key=”#result.id"：使用返回后的id
     *      为什么是没更新前的?【1号员工没有在缓存中更新】

     */
    @CachePut(value = "dep", key = "#department.id")
    public void updateDep(Department department) {
        System.out.println("员工更新了" + department);
        departmentMapper.updateDep(department);
    }

    public void insertDep(Department department) {

    }

    /**
     * create by: balsam
     *
     * @CacheEvict:缓存清除 key:指定要清除的数据
     * aLLEntries = true:指定清除这个缓存中所有的数据
     * beforeInvocation = false:缓存的清除是否在方法之前执行
     * 默认代表是在方法执行之后执行,如果出现异常就不会清除
     *  beforeInvocation = true:
     *      代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(value = "dep")
    public void deleteById(Long id) {

    }
}
