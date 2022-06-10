<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				</a>  <a href="../index.html"> <i class="fas fa-sign-out-alt"></i>
					Logout
				</a>
			</header>
			<div class="main-content">
				<div class="main-content-title">
					<h2>Gerenciar Peso</h2>
					<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
				</div>
				<div class="panel-row">
					<div class="dynamic-content">
						<div class="container">
							<form action="peso" method="post">
							<input type="hidden" value="cadastrar" name="acao">

								<div class="form-group">
									<label for="vl_peso" class="form-label">Novo Peso</label> <input
										type="text" class="form-control" name="vl_peso" id="vl_peso"
										placeholder="Digite o Novo Peso" />
								</div>
								<div class="form-group">
									<label for="dt_peso" class="form-label">Data de Pesagem</label>
									<input type="text" class="form-control" name="dt_peso"
										id="dt_peso" placeholder="(DD/MM/YYYY)" />
								</div>
								<p></p>
								<input type="submit" class="btn btn-primary" value="Cadastrar"></input>
							</form>
							<p></p>
							<div></div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</body>
</html>