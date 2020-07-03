package com.guixiang.zhanjun.test;

import com.guixiang.zhanjun.pojo.User;
import com.guixiang.zhanjun.service.UserService;
import com.guixiang.zhanjun.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void loginUser() {
        System.out.println(userService.loginUser(new User("root", "rot", null)));
    }

    @Test
    public void registUser() {
        System.out.println(userService.registUser(new User("this", "this", "this@189.com")));
        System.out.println(userService.registUser(new User("thisMan", "this", "thisMan@189.com")));
    }

    @Test
    public void existsUserName() {
        System.out.println(userService.existsUserName("admin"));
    }
}