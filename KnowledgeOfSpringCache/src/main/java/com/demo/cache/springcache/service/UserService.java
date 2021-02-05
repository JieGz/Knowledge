package com.demo.cache.springcache.service;

import com.demo.cache.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Spring Cache
 *
 * @author 揭光智
 * @date 2019/03/10
 */
@Service
public class UserService {

    /**
     * 注意0001字符串需要使用单引号引用.
     * cacheNames和values是一样的,表示当前的的返回值为被缓存在哪一个Cache对象上.对应的是cache的名字
     * key属性是用来指定Spring缓存方法的返回结果时对应的key的.使用SpringEL表达式
     * condition属性指定发生的条件
     *
     * @param userId 用户的id
     * @return {@link User} 对象
     */
    @Cacheable(cacheNames = "users", key = "#userId", condition = "#userId.equals('0001')")
    public User getUserById(String userId) {
        System.out.println("read query user ... => " + userId);
        return getFromDb(userId);
    }

    private User getFromDb(String userId) {
        System.out.println("real querying db... => " + userId);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User(userId);
    }


    /**
     * allEntries 表示是否需要清除缓存中的所有元素,默认为false,
     * 当指定allEntries为true时,Spring cache将忽略指定的key,
     * 有的时候我们需要Cache一下清除所有的元素,这比一个一个地清除,效率高.
     * <p>
     * beforeInvocation: 清除缓存操作默认是在方法成功执行之后再触发的,如果方法执行过程中因抛出异常而未能成功返回时,清除操作是不会被触发的
     * 可以通过beforeInvocation改变清除缓存操作发生的时机,Spring在调用该方法之前清楚缓存中的指定元素.
     *
     * @param userId 用户的id
     */
    @CacheEvict(cacheNames = "users", key = "#userId", condition = "#userId.equals('0001')"/**,allEntries = true*/, beforeInvocation = true)
    public void clearCache(String userId) {
        System.out.println("准备清除缓存");
        int i = 1 / 0;
        System.out.println("准备缓存结束");
    }
}
