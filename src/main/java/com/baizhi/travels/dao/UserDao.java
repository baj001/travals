package com.baizhi.travels.dao;

import com.baizhi.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sfkstart
 * @create 2021-11-25-19:49
 */
@Mapper
public interface UserDao {

    //根据用户名去查询用户
    User findByUsername(String username);

    //注册用户
    void save(User user);
}
