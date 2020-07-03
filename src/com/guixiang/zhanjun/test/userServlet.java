package com.guixiang.zhanjun.test;

import java.lang.reflect.Method;

public class userServlet {


    public void add(){
        System.out.println("add()...");
    }
    public void modify(){
        System.out.println("modify()...");
    }
    public void update(){
        System.out.println("update()...");
    }
    public void delate(){
        System.out.println("delate()...");
    }


    public static void main(String[] args) {
        String[] methods = new String[]{"add", "modify", "update", "delate"};
        try {

            for (int i = 0; i < 4; i++) {
                Method method = userServlet.class.getDeclaredMethod(methods[i]);
                System.out.println(method);
                method.invoke(new userServlet());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
