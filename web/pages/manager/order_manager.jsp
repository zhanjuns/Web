<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--锁定base标签，以及引入的css，jQuery--%>
    <%@include file="/common/head.jsp" %>
</head>
<body>

<%--管理菜单抽取出来--%>
<%@include file="/common/manage_menu.jsp" %>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>

        </tr>
        <tr>
            <td>2015.04.23</td>
            <td>90.00</td>
            <td><a href="#">查看详情</a></td>
            <td><a href="#">点击发货</a></td>
        </tr>

        <tr>
            <td>2015.04.20</td>
            <td>20.00</td>
            <td><a href="#">查看详情</a></td>
            <td>已发货</td>
        </tr>

        <tr>
            <td>2014.01.23</td>
            <td>190.00</td>
            <td><a href="#">查看详情</a></td>
            <td>等待收货</td>
        </tr>
    </table>
</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</body>
</html>