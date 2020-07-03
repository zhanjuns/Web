package com.guixiang.zhanjun.service;

import com.guixiang.zhanjun.pojo.User;

public interface UserService {


    /**
     * 登录用户
     *
     * @param user
     * @return
     */
    public User loginUser(User user);

    /**
     * 注册用户
     *
     * @param user
     * @return 返回true表示注册成功
     */
    public boolean registUser(User user);

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return 存在返回true，不存在返回false
     */
    public boolean existsUserName(String username);
}
