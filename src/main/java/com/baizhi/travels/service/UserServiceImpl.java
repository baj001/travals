package com.baizhi.travels.service;

import com.baizhi.travels.dao.UserDao;
import com.baizhi.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sfkstart
 * @create 2021-11-25-20:11
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        User userDB = userDao.findByUsername(user.getUsername());
        if(userDB!=null){
            if(userDB.getPassword().equals(user.getPassword())){
                return userDB;
            }else{//抛出异常
                throw new RuntimeException("密码输入错误！！！");
            }
        }else {//说明没有这个用户名
            throw new RuntimeException("用户名输入错误！！！");
        }
    }

    @Override
    public void register(User user) {
        if(userDao.findByUsername(user.getUsername()) == null){
            userDao.save(user);
        }else {
            throw new RuntimeException("用户名已经存在~~~");
        }

    }
}
