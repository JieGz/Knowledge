package com.demo.jpa.dao;

import com.demo.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 揭光智
 * @date 2020/03/19
 */
public interface UserDao extends CrudRepository<User, Integer> {
}
