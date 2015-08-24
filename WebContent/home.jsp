<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to our banking application !!</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="metro-bootstrap-master/css/metro-bootstrap.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<header class="clearfix"></header>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle Navigation</span> <span class="icon-bar"></span>
			
		</button>
		<a class="navbar-brand" href="#">Banking Application JSP</a>
	</div>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="home.jsp">Home</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="active"><a><form action="logout" method="post">
					<input style="font-size: larger; background-color: #2E9AFE" type="submit" value="Logout">
				</form></a></li>
		</ul>
	</div>
	</nav>
	<%@ page import="net.mv.bankapp.domain.Account"%>
	<%@ page import="net.mv.bankapp.dao.AccountDao"%>
	<%@ page import="net.mv.bankapp.dao.AppUserDao"%>
	<%@ page import="net.mv.bankapp.domain.AppUser"%>
	<%
	    String user = null;
	    if (session.getAttribute("Username") == null)
	    {
	        response.sendRedirect("login.jsp");
	    }
	    else
	        user = (String) session.getAttribute("Username");
	    AccountDao accDao = new AccountDao();
	    AppUserDao userDao = new AppUserDao();
	    AppUser aUser = userDao.retrieveAppUser(user);
	    Account currentAccount = accDao.retrieveAccount(aUser.getId());
	    long AccountNum = currentAccount.getId();
	    double AccountBalance = currentAccount.getBalance();
	    String userName = null;
	    String sessionID = null;
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null)
	    {
	        for (Cookie cookie : cookies)
	        {
	            if (cookie.getName().equals("Username"))
	                userName = cookie.getValue();
	            if (cookie.getName().equals("JSESSIONID"))
	                sessionID = cookie.getValue();
	        }
	    }
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<h1 class="bg-primary img-rounded text-center">Account
					Information</h1>
				<div class="form-group">
					<form action="transaction" method="post">
						<table class="table table-striped" border="0">
							<tr>
								<td><label for="accountNum" class="control-label">Account
										Number</label></td>
								<td><label for="lblAccountNum" class="control-label"><%=AccountNum%></label></td>
							</tr>
							<tr>
								<td><label for="currentBalance" class="control-label">Current
										Balance</label></td>
								<td><label for="lblCurrentBalance" class="control-label"><%=AccountBalance%></label></td>
							</tr>
							<tr>
								<td><label for="amount" class="control-label">Amount</label></td>
								<td><input name="AccountNum" type="hidden"
									value="<%=AccountNum%>"> <input name="amount"
									class="form-control" placeholder="Enter number" autofocus></td>
							</tr>
							<tr>
								<td><label for="userService" class="control-label">Service</label></td>
								<td><input type="radio" name="rbtnUserService" id="deposit"
									value="deposit">Deposit <br /> <input type="radio"
									name="rbtnUserService" id="withdraw" value="withdraw">Withdrawn</td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="Submit" class="btn btn-default btn-lg"
									id="processUserService"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>