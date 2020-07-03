<%@ page contentType="text/html;charset=UTF-8" language="java" %>r
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
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
		<%@include file="/common/logo.jsp"%>
			<span class="wel_word">结算</span>
		<%--将页面相同的东西提取出来--%>
		<%@include file="/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		
	
	</div>

	<%--静态包含页脚部分--%>
	<%@include file="/common/tail.jsp"%>
</body>
</html>