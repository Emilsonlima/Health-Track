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
	<div class="register-parent">
		<div class="register">
			<div class="register-tittle">
				<a href="index.jsp"> <img src="resources/images/heartbeat.png"
					alt="" />
				</a>
				<h2>Health Track</h2>
				<c:if test="${not empty msg }">
					<div class="alert alert-success">${msg}</div>
				</c:if>
				<c:if test="${not empty erro }">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
			</div>
			<form action="perfil" method="post">
				<input type="hidden" value="cadastrar" name="acao">
				
				<div class="form-group">
					<label for="nm_perfil" class="form-label">Nome</label> <input
						type="text" class="form-control" name="nm_perfil" id="nm_perfil"
						placeholder="Nome completo" />
				</div>
				<div class="form-group">
					<label for="nr_idade" class="form-label">Idade</label> <input
						type="text" class="form-control" name="nr_idade" id="nr_idade"
						placeholder="Idade" />
				</div>
				<div class="form-group">
					<label for="nm_sexo" class="form-label">Sexo</label> <input
						type="text" class="form-control" name="nm_sexo" id="nm_sexo"
						placeholder="Sexo" />
				</div>
				<div class="form-group">
					<label for="vl_altura" class="form-label">Altura</label> <input
						type="text" class="form-control" name="vl_altura" id="vl_altura"
						placeholder="Altura" />
				</div>
				<div class="form-group">
					<label for="nm_email" class="form-label">Email</label> <input
						type="email" class="form-control" name="nm_email" id="nm_email"
						aria-describedby="emailHelp" />
				</div>
				<div class="form-group">
					<label for="nr_senha" class="form-label">Senha</label> <input
						type="password" class="form-control" name="nr_senha" id="nr_senha" />
				</div>
				<p></p>
				<input type="submit" class="btn btn-primary"  value="Cadastrar">
			</form>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
	<script>
      function register() {
        window.location.href = 'perfil.jsp'
        event.preventDefault()
      }
    </script>
</body>
</html>