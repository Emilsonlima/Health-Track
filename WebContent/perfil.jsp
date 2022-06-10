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
				</a> <a href="index.jsp"> <i class="fas fa-sign-out-alt"></i>
					Logout
				</a>
			</header>
			<div class="main-content">
				<div class="main-content-title">
					<h2>EDITAR PERFIL</h2>
					<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
					
				</div>
				<div class="perfil-parent">
					<div class="perfil">
						<div class="perfil-form">
							<form action="perfil" method="post">
							<input type="hidden" value="editar" name="acao"> <input
									type="hidden" value="${perfil.id_perfil }" name="id_perfil">
								<div class="form-group">
									<label for="id_perfil" class="form-label">Nome Completo</label> <input
										type="text" class="form-control" value="${perfil.id_perfil }" name ="id_perfil "id="id_perfil" />
								</div>
								<div class="form-group">
									<label for="nr_idade" class="form-label">Idade</label> <input
										type="Idade" class="form-control" value="${perfil.nr_idade }" id="nr_idade" />
								</div>
								<div class="form-group">
									<label for="nm_sexo" class="form-label">Sexo</label> <input
										type="text" class="form-control" value="${perfil.nm_sexo }" id="nm_sexo" />
								</div>
								
								<div class="form-group">
									<label for="vl_altura" class="form-label">Altura</label> <input
										type="altura" class="form-control" value="${perfil.vl_altura }" id="vl_altura" />
								</div>
								<input type="hidden" value="editar" name="acao"> <input
									type="hidden" value="${usuario.usuario_id }" name="usuario_id">
									
								<div class="form-group">
									<label for="nm_email" class="form-label">Email</label> <input
										type="email" class="form-control" value="${usuario.nm_email }" id="nm_email" />
								</div>
								<div class="form-group">
									<label for="nr_senha" class="form-label">Senha</label> <input
										type="password" class="form-control" value="${usuario.nr_senha }" id="nr_senha" />
								</div>
								<p></p>
								<input type="submit" class="btn btn-primary" value="Salvar">
							</form>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>