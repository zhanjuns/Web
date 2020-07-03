<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--锁定base标签，以及引入的css，jQuery--%>
    <%@include file="/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            //给删除绑定单击事件
            $("a.delItem").click(function () {
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？");
            });
            //给清空绑定单击事件
            $("a.clear").click(function () {
                return confirm("你确定要清空购物车吗？");
            });


            //给数量输入框绑定内容改变事件
            $(".updateCount").change(function () {
                var isUpdate = confirm("你确定要修改【" + $(this).parent().parent().find("td:first").text() + "】的数量为【" + $(this).val() + "】吗？");
                if (!isUpdate) {
                    this.value = this.defaultValue;
                } else {
                    location.href = "/book/cartServlet?action=update&id=" + $(this).attr("bookId") + "&count=" + this.value;
                }
            });

            $("a.createOrder").click(function () {
                if(${empty sessionScope.user}){
                    if (confirm("还没有登录哟...立即去登录？")){
                        this.href = "/book/pages/user/login.jsp";
                    }else{
                        return false;
                    }
                }
            })

        });
    </script>

</head>
<body>

<div id="header">
    <%--页面的logo--%>
    <%@include file="/common/logo.jsp" %>
    <span class="wel_word">购物车</span>
    <%--将页面相同的东西提取出来--%>
    <%@include file="/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:if test="${not empty sessionScope.cart.items}">

            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <input class="updateCount" bookId="${entry.value.id}" style="width: 80px" type="text"
                               value="${entry.value.count}"/></td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="delItem" href="cartServlet?action=delItem&bookId=${entry.value.id}">删除</a></td>
                </tr>

            </c:forEach>

        </c:if>

        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp">购物车空空如也...快去浏览商品吧~~</a></td>
            </tr>
        </c:if>


    </table>

    <c:if test="${not empty sessionScope.cart.items}">

        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class="clear" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a class="createOrder" href="orderServlet?action=createOrder&userId=${sessionScope.user.id}">去结账</a></span>
        </div>

    </c:if>

</div>

<%--静态包含页脚部分--%>
<%@include file="/common/tail.jsp" %>
</body>
</html>