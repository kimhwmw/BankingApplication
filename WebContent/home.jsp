<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Welcome to our banking application !!</title>
</head>
<body>
	<%@ page import="net.mv.bankapp.domain.Account"	%>
	<%@ page import="net.mv.bankapp.dao.AccountDao" %>
	<%@ page import="net.mv.bankapp.dao.AppUserDao" %>
	<%@ page import="net.mv.bankapp.domain.AppUser" %>
	<%
		String user = null;
		if (session.getAttribute("Username") == null) {
			response.sendRedirect("login.jsp");
		} else
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
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Username"))
					userName = cookie.getValue();
				if (cookie.getName().equals("JSESSIONID"))
					sessionID = cookie.getValue();
			}
		}
	%>

	<form action="logout" method="post">
		<input type="submit" value="Logout">
	</form>
	<p></p>
	<p>Account number: <%=AccountNum %></p>
	<p>Current Balance: <%=AccountBalance %></p>
	<form action="transaction" method="post">

		<div class="form-group">
			<label>Enter amount of money: </label>
			<input name ="AccountNum" type = "hidden" value = "<%=AccountNum %>">
			
			<input name = "amount" class="form-control" placeholder="Enter number">
		</div>
		<div class="form-group">
			<div class="checkbox">
				<label> <input name= "deposit" type="radio" value="">Deposit
				</label>
			</div>
			<div class="checkbox">
				<label> <input name= "withdraw" type="radio" value="">Withdraw
				</label>
			</div>
		</div>
		<div class = "form-group">
			<button>Summit</button>
		</div>
	</form>

</body>
</html>