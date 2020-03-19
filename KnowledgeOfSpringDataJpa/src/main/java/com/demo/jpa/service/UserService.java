package com.demo.jpa.service;

import com.demo.jpa.dao.UserDao;
import com.demo.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 揭光智
 * @date 2020/03/19
 */
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void saveUser(User user) {
        userDao.save(user);
    }

    public User findUserById(Integer id) {
        return userDao.findById(id).orElse(null);
    }
}
