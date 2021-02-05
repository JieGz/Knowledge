package com.demo.cache.springcache.web;

import com.demo.cache.entity.User;
import com.demo.cache.springcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 揭光智
 * @date 2019/03/10
 */
@RestController
public class UserController {

    private static final String USER_ID = "0001";

    @Autowired
    private UserService service;

    @GetMapping("/get")
    public String getUserById() {
        User user = service.getUserById(USER_ID);
        return user.getUserName();
    }


    @GetMapping("/clear")
    public String clearCache() {
        service.clearCache(USER_ID);
        return "Ok";
    }

    @GetMapping("/update")
    public String update() {
        return service.updateCache(USER_ID).getUserName();
    }
}
