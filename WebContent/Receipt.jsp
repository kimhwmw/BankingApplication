<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Here is your Receipt</h1>
	<% 
		String name = (String)session.getAttribute("Username");
	%>
	
	<p>Account number: ${AccountNum}</p>
	<p>Old Balance: ${AccountOldBalance}</p>
	<p>New Balance: ${AccountNewBalance}</p>
	<a href="home.jsp">Home</a>
</body>
</html>