package com.demo.cache.springcache.service;

import com.demo.cache.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 揭光智
 * @date 2019/03/10
 */
@Service
public class UserService {

    @Cacheable(cacheNames = "users")
    public User getUserById(String userId) {
        System.out.println("read query user ... => " + userId);
        return getFromDb(userId);
    }

    private User getFromDb(String userId) {
        System.out.println("real querying db... => " + userId);
        return new User(userId);
    }
}
