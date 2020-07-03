<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
r
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
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

<div id="header">
    <%--页面的logo--%>
    <%@include file="/common/logo.jsp" %>
    <span class="wel_word">我的订单</span>
    <%--将页面相同的东西提取出来--%>
    <%@include file="/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>

        <c:if test="${empty requestScope.myOrders}">
            <tr>
                <td colspan="5"><a href="index.jsp">我的订单空空如也...快去浏览商品吧~~</a></td>
            </tr>
        </c:if>

        <c:if test="${not empty requestScope.myOrders}">
            <c:forEach items="${requestScope.myOrders}" var="order">
                <tr>
                    <td>${order.createTime}</td>
                    <td>${order.price}</td>
                    <td>${order.status==0?"未发货":order.status==1?"已发货":"已完成"}</td>
                    <td><a href="#">查看详情</a></td>
                </tr>
            </c:forEach>
        </c:if>


    </table>


</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</body>
</html>