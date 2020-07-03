<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--锁定base标签，以及引入的css，jQuery--%>
    <%@include file="/common/head.jsp" %>

    <script type="text/javascript">

        $(function () {

            //给删除按钮加提示框
            $("a.deleteClass").click(function () {
                //confirm是确认提示框函数
                //有两个按钮，一个确认，一个取消
                //返回true确认，返回false取消

                //在事件中有this对象，表示正在操作的DOM对象
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？");

            });

        });

    </script>


</head>
<body>

<%--管理菜单抽取出来--%>
<%@include file="/common/manage_menu.jsp" %>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>


    <%--分页--%>
    <%@include file="/common/page_nav.jsp" %>
</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</body>
</html>