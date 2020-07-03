<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fighting会员注册页面</title>
    <%--锁定base标签，以及引入的css，jQuery--%>
    <%@include file="/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <%--页面的logo--%>
    <%@include file="/common/logo.jsp" %>
    <span class="wel_word">注册成功</span>
    <%--将页面相同的东西提取出来--%>
    <%@include file="/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>注册成功! <a href="index.jsp">转到主页</a></h1>

</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp"%>
</body>
</html>