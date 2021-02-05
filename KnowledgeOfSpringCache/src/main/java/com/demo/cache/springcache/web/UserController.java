package com.demo.cache.springcache.web;

import com.demo.cache.entity.User;
import com.demo.cache.springcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 揭光智
 * @date 2019/03/10
 */
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/get")
    public String getUserById() {
        User user = service.getUserById("0001");
        return user.getUserId();
    }



    @GetMapping("/clear")
    public String clearCache() {
        service.clearCache("0001");
        return "Ok";
    }
}
