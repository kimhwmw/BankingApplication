<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Receipt</title>
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
				<span class="sr-only">Toggle Navigation</span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Banking Application JSP</a>
		</div>
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="home.jsp">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><form action="logout" method="post">
						<input style="font-size: larger; background-color: #2E9AFE"
							type="submit" value="Logout">
					</form>
					</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<h1 class="bg-primary img-rounded text-center">Your Receipt</h1>
				<%
				    String name = (String) session.getAttribute("Username");
				%>
				<table class="table table-striped" border="0">
					<thead>
						<tr>
							<th>Account Number</th>
							<th>Old Balance</th>
							<th>New Balance</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Account number: ${AccountNum}</td>
							<td>${AccountOldBalance}</td>
							<td>${AccountNewBalance}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>