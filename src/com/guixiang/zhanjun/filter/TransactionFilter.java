package com.guixiang.zhanjun.filter;

import com.guixiang.zhanjun.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        try {
            chain.doFilter(req, resp);

            //提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();

            //回滚事务
            JdbcUtils.rollbackAndClose();
            //继续抛出错误，让tomcat显示错误页面
            throw new RuntimeException(e);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
