package com.baizhi.travels.service;

import com.baizhi.travels.entity.User;

/**
 * @author sfkstart
 * @create 2021-11-25-20:08
 */
public interface UserService {

    //在业务层开发一个用户登录的接口
    User login(User user);

    //用来保存User对象
    void register(User user);
}
