<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/16 0016
  Time: 下午 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <div>
            <span>欢迎光临fighting商城</span>
            <a href="index.jsp">返回</a>
        </div>
    </c:when>

    <c:when test="${not empty sessionScope.user}">
        <div>
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临fighting商城</span>
            <a href="orderServlet?action=myOrders&userId=${sessionScope.user.id}">我的订单</a>
            <a href="userServlet?action=logout">注销</a>
            <a href="index.jsp">返回</a>
        </div>
    </c:when>

</c:choose>

