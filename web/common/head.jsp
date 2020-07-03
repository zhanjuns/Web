<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/16 0016
  Time: 下午 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--动态获取base地址！！！--%>
<%
    String bathPath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

%>

<%--<%=bathPath%>--%>
<%--写base 标签，固定跳转相对路径--%>
<base href="<%=bathPath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
