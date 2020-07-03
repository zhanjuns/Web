package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.dao.UserDao;
import com.guixiang.zhanjun.dao.impl.UserDaoImpl;
import com.guixiang.zhanjun.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        User admin = userDao.queryUserByUsername("admin");
        if (admin != null) {
            System.out.println("用户名可用-" + "用户名：" + admin.getUsername() + "密码：" + admin.getPassword());

        } else {
            System.out.println("用户名不存在");
        }
    }

    @Test
    public void saveUser() {
        int i = userDao.saveUser(new User("root", "root", "root@163.com"));
        System.out.println(i > 0 ? "保存成功" : "保存失败");


    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("root", "root");
        System.out.println(user == null ? "用户名或密码错误，登录失败" : "登录" + user.getUsername() + "成功");
    }
}