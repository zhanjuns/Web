<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <%--锁定base标签，以及引入的css，jQuery--%>
    <%@include file="/common/head.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<%--管理菜单抽取出来--%>
<%@include file="/common/manage_menu.jsp" %>

<div id="main">
    <h1>欢迎管理员进入后台管理系统</h1>
</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</body>
</html>