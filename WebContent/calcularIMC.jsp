<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="Header.jsp"%>
<title>Health Track</title>
</head>
<body>
	<div class="flex-dashboard">
		<sidebar>
		<div class="sidebar-title">
			<img src="resources/images/heartbeat.png" alt="logo-healthtrack" />
			<h2>Health Track</h2>
		</div>
		<%@ include file="Menu.jsp"%>
		</sidebar>
		<main>
			<header>
				<a> <i></i>
				</a>  <a href="index.jsp"> <i class="fas fa-sign-out-alt"></i> Logout
				</a>
			</header>
			<div class="main-content">
				<div class="imc-parent">
					<h2>Informe seus dados</h2>
					<div class="imc">

						<form method="GET" action="imc">
							Altura:<input type="text" class="form-control" name="vl_altura" id="vl_altura" />
							<p></p>
							<br> Peso:<input type="text" class="form-control" name="vl_peso" id="vl_altura" /><br>
							<p></p>
							<input type="submit" class="btn btn-primary" value="Consultar"></input>
						</form>

					</div>
					
				</div>
			</div>
		</main>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>