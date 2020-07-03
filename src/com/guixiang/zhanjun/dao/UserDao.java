package com.guixiang.zhanjun.dao;

import com.guixiang.zhanjun.pojo.User;

public interface UserDao {


    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return  如果返回null，说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1，保存失败
     */
    public int saveUser(User user);


    /**
     * 根据用户名和密码查询用户信息
     * @param username  用户名
     * @param password  密码
     * @return  如果返回null，说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String username, String password);


}
