<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="login">
		<div class="login-form">
			<div class="login-form-wrapper">
				<div class="login-tittle">
					<h2>Log in</h2>
					<a href="cadastro-perfil.jsp">Inscreva-se</a>
				</div>
				<span class="navbar-text text-danger" style="margin-right:10px" >
	        ${erro }
	  	</span>
				
				<form action="login" method="post">
					<div class="mb-3">
						<label for="nm_email" class="form-label">Email</label> <input
							type="email" class="form-control mr-sm2" name= "nm_email" id="nm_email"
							aria-describedby="emailHelp" placeholder="Digite seu email" />
					</div>
					<div class="mb-3">
						<label for="nr_senha" class="form-label">Senha</label> <input
							type="password" class="form-control mr-sm2" name="nr_senha" id="nr_senha"
							placeholder="Digite sua senha" />
					</div>
					<button type="submit" onclick="window.location.href='perfil.jsp'" class="btn btn-outline-success my-2 my-sm-0">Login</button>
				</form>
			</div>
		</div>
		<div class="banner-login">
			<img src="resources/images/heartbeat.png" alt="logo-health-track" />
			<h2>Health Track</h2>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
	<script>
      let button = document.querySelector('form button.btn')
      button.addEventListener('click', () => {
        location.href = 'dashboard.jsp'
      })
    </script>

</body>
</html>