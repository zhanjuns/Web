<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fighting会员注册页面</title>
    <%--锁定base标签，以及引入的css，jQuery--%>
    <%@include file="/common/head.jsp" %>
    <script type="text/javascript">

        $(function () {

            //给验证码图片绑定单击事件
            $(".code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });

            //给注册绑定单击事件
            $("#sub_btn").click(function () {
                //对所有的信息进行验证
                //验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //1.得到输入的用户名
                var usernameText = $("#username").val();
                //2.创建正则表达式
                var Patt = /^\w{5,12}$/;
                //3.使用test方法验证
                if (!Patt.test(usernameText)) {
                    //4.提示用户结果
                    $(".errorMsg").text("用户名不合法");
                    return false;
                }


                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //1.得到输入的密码
                var passwordText = $("#password").val();
                //3.使用test方法验证
                if (!Patt.test(passwordText)) {
                    //4.提示用户结果
                    $(".errorMsg").text("密码不合法");
                    return false;
                }

                // 验证确认密码：和密码相同
                var repasswordText = $("#repwd").val();

                $("#repwd").blur(function () {
                    if (repasswordText != passwordText) {
                        $(".errorMsg").text("确认密码和密码不一致");
                    }

                });
                if (repasswordText != passwordText) {
                    $(".errorMsg").text("确认密码和密码不一致");
                    return false;
                }


                // 邮箱验证：xxxxx@xxx.com
                //1.得到输入的邮箱
                var emailText = $("#email").val();
                //2.创建正则表达式
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3.使用test方法验证
                if (!emailPatt.test(emailText)) {
                    //4.提示用户结果
                    $(".errorMsg").text("邮箱不合法");
                    return false;
                }

                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。


                $(".errorMsg").text("")
            });

            //给用户名输入框绑定失去焦点事件，发送Ajax请求判断用户名是否可用
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("/book/userServlet", "action=ajaxExistsUserName&username=" + username, function (data) {
                   if (data.existsUserName) {
                       $("span.errorMsg").text("用户名已存在");
                   }else{
                       $("span.errorMsg").text("用户名可用");
                   }
                });
            });
        })

    </script>

    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">fighting</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册fighting会员</h1>
                    <span class="errorMsg">
                        ${requestScope.error}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <label>用户名称：</label>
                        <%--隐藏域，表示注册页面--%>
                        <input type="hidden" name="action" value="regist"/>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 80px;" name="code" id="code"/>
                        <img class="code_img" alt="" src="kaptcha.jpg" style="width: 150px;height: 40px; float: right; margin-right: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</html>