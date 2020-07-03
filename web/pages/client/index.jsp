<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商城首页</title>
    <%--页面的logo--%>
    <%@include file="/common/head.jsp" %>

    <script type="text/javascript">

        $(function () {
            //给加入购物车添加点击事件
            $("button.addToCart").click(function () {

                var bookId = $(this).attr("bookId");
                var pageNo = $(this).attr("pageNo");

                //location.href = "cartServlet?action=addItem&bookId=" + bookId + "&pageNo=" + pageNo;
                $.getJSON("/book/cartServlet", "action=addItemForAjax&bookId=" + bookId + "&pageNo=" + pageNo, function (data) {
                    var totalCount = data.totalCount;
                    var lastName = data.lastName;
                    // alert(data.lastName);
                    $("#total_count").text("您的购物车中有" + totalCount + "件商品");
                    $("#last_name").text("您刚刚将《" + lastName + "》加入到了购物车中");
                });

            });

        });

    </script>


</head>
<body>

<div id="header">
    <%--页面的logo--%>
    <%@include file="/common/logo.jsp" %>
    <span class="wel_word">网上商城</span>
    <div>
        <%--如果用户没有登录，显示登录注册菜单，登录之后隐藏--%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a>
            <a href="pages/user/regist.jsp">注册</a>
        </c:if>
        <%--如果用户登录，显示用户登录之后的信息--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临fighting商城</span>
            <a href="userServlet?action=logout">注销</a>
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">

            <c:if test="${not empty sessionScope.cart}">
                <span id="total_count">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
            </c:if>
            <c:if test="${empty sessionScope.cart}">
                <span id="total_count">您的购物车中还没有商品哟~</span>
            </c:if>

            <c:if test="${not empty sessionScope.book}">
                <div id="last_name" >
                    <%--您刚刚将<span style="color: red">${sessionScope.book}</span>加入到了购物车中--%>
                    您刚刚将《${sessionScope.book}》加入到了购物车中
                </div>
            </c:if>
            <c:if test="${empty sessionScope.book}">
                <div id="last_name"></div>
            </c:if>


<%--            <c:if test="${not empty sessionScope.cart}">
                <span id="total_count">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
            </c:if>
            <c:if test="${not empty sessionScope.book}">
                <div id="last_name">您刚刚将《${sessionScope.book}》加入到了购物车中</div>
            </c:if>--%>


        </div>

        <c:forEach items="${requestScope.page.items}" var="book">


            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button pageNo="${requestScope.page.pageNo}" bookId="${book.id}" class="addToCart">加入购物车
                        </button>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>

    <%--分页--%>
    <%@include file="/common/page_nav.jsp" %>

</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</body>
</html>