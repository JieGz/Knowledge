package com.demo.jpa.service;

import com.demo.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author 揭光智
 * @date 2020/03/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setName("Luke");
        user.setAge(18);
        userService.saveUser(user);
    }

    @Test
    public void findUserByIdTest() {
        System.out.println(LocalDateTime.now().getHour());
        User user = userService.findUserById(1);
        System.out.println(user);
    }
}
