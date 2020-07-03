<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fighting会员登录页面</title>
	<%--锁定base标签，以及引入的css，jQuery--%>
	<%@include file="/common/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}

	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
			<%@include file="/common/logo.jsp" %>

            <%--将页面相同的东西提取出来--%>
			<%@include file="/common/login_success_menu.jsp"%>
		</div>

		<div id="main">

			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>

		</div>

		<%--静态包含页脚部分--%>
		<%@include file="/common/tail.jsp"%>
</body>
</html>