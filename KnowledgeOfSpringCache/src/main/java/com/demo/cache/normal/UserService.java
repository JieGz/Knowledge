package com.demo.cache.normal;

import com.demo.cache.entity.User;

/**
 * @author 揭光智
 * @date 2019/03/10
 */
public class UserService {
    private CacheManager<User> cacheManager;

    public UserService() {
        cacheManager = new CacheManager<>();
    }

    public User getUserById(String userId) {
        User result = cacheManager.getValue(userId);
        if (result != null) {
            System.out.println("get from cache ... => " + userId);
            return result;
        }

        result = getFromDb(userId);

        if (result != null) {
            cacheManager.addOrUpdateCache(userId, result);
        }

        return result;
    }


    public void reload() {
        cacheManager.evictCache();
    }

    private User getFromDb(String userId) {
        System.out.println("real querying db... => " + userId);
        return new User(userId);
    }
}
