<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/13 0013
  Time: 下午 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>输出99乘法表</title>
    <style type="text/css">

        table{
            width: 680px;
            background-color: bisque;
        }


        h1{
            background-color: #39987c;
        }

    </style>
</head>
<body>

<h1 align="center">九九乘法口诀</h1>

<table align="center">
    <%--练习使用jsp输出99乘法表--%>
    <%
        for (int i = 1; i < 10; i++) {%>
    <tr>
        <% for (int j = 1; j <= i; j++) { %>
        <td align="left"><%=j + "*" + i + "=" + (i * j)%>
        </td>
    <%}%>
    </tr>
    <%}%>

</table>
</body>
</html>
