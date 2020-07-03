package com.guixiang.zhanjun.service.impl;

import com.guixiang.zhanjun.dao.UserDao;
import com.guixiang.zhanjun.dao.impl.UserDaoImpl;
import com.guixiang.zhanjun.pojo.User;
import com.guixiang.zhanjun.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean registUser(User user) {
        return userDao.saveUser(user) > 0;
    }

    @Override
    public boolean existsUserName(String username) {
        User user = userDao.queryUserByUsername(username);
        return user != null;
    }
}
