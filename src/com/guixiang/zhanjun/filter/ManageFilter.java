package com.guixiang.zhanjun.filter;

import com.guixiang.zhanjun.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        User user = (User) req.getSession().getAttribute("user");

        if (user != null && "admin".equals(user.getUsername())){
            chain.doFilter(request, response);
        }else{
            req.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
