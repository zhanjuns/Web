package com.guixiang.zhanjun.web;

import com.google.gson.Gson;
import com.guixiang.zhanjun.pojo.User;
import com.guixiang.zhanjun.service.UserService;
import com.guixiang.zhanjun.service.impl.UserServiceImpl;
import com.guixiang.zhanjun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();


    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        String code = req.getParameter("code");


        //获取form的信息，之后由工具类导入到user中
        User user = WebUtils.copyPararmToBean(req.getParameterMap(), new User());

        //2.检查验证码是否正确
        //自客户端得到验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //马上删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if (token != null && token.equalsIgnoreCase(code)) {
            //正确
            //3.检查用户名是否可用
            if (userService.existsUserName(user.getUsername())) {
                //不可用
                //跳回注册页面
                req.setAttribute("error", "用户名已存在..");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());

//                System.out.println("用户名" + user.getUsername() + "已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用
                //保存数据库
                boolean registUser = userService.registUser(user);
                if (registUser) {
                    //保存成功
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                } else {
                    //保存失败
                    System.out.println("保存数据库失败");
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                }
            }
        } else {

            if (token == null) {
                req.setAttribute("error", "请不要重复提交表单...");
            } else {

                //验证码不正确

                req.setAttribute("error", "验证码错误...");
            }

            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());

//            System.out.println("验证码" + code + "错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //1.先验证用户名是否存在
        boolean existsUserName = userService.existsUserName(username);
        if (existsUserName) {
            //用户名存在
            User loginUser = userService.loginUser(new User(username, password, null));
            if (loginUser != null) {
                //登录成功---跳转到登陆成功页面

                //添加登录成功的session数据
                request.getSession().setAttribute("user", loginUser);
//                Cookie loginCookie = new Cookie("username", username);
                //设置cookie一周内有效
//                loginCookie.setMaxAge(60 * 60 * 24 * 7);
//                response.addCookie(loginCookie);

                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
            } else {
                //登录失败---跳转到登录页面
                System.out.println("登录失败，密码错误");
                //登录失败，将错误信息以及回显信息保存到request域中，因为时请求转发，下一个页面也可以得到

                request.setAttribute("error", "登录失败，用户名或密码错误");
                request.setAttribute("username", username);
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        } else {
            //用户名不存在
            System.out.println("登录失败，用户名不存在");


            //登录失败，将错误信息以及回显信息保存到request域中，因为时请求转发，下一个页面也可以得到

            request.setAttribute("error", "登录失败，用户名不存在");
            request.setAttribute("username", username);

            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }

    }

    /**
     * 注销操作
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("到达注销");
        //1.销毁session
        request.getSession().invalidate();
        //2.重定向
        response.sendRedirect(request.getContextPath());

    }


    protected void ajaxExistsUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数username
        String username = request.getParameter("username");
        boolean existsUserName = userService.existsUserName(username);
        //返回结果封装为map对象

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUserName", existsUserName);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
}
