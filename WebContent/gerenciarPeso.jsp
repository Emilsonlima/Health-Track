<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				</a> > <a href="index.jsp"> <i class="fas fa-sign-out-alt"></i>
					Logout
				</a>
			</header>
			<div class="main-content">
				<div class="main-content-title">
					<h2>Gerenciar Peso e Altura</h2>
				</div>
				<div class="panel-row">
					<button class="panel panel-50" a-view="cadastrarPeso"
						onclick="window.location.href='cadastrarpesos.jsp'" a-folder="peso" >
						<i class="fas fa-plus"></i> Cadastrar peso
					</button>
					<button class="panel panel-50" a-view="visualizarPeso"
						onclick="window.location.href='peso?acao=listar'" a-folder="peso">
						<i class="fas fa-table"></i> Visualizar peso
					</button>
				</div>
				<div class="content" id="ajax-content"></div>
			</div>
		</main>
	</div>
	<%@ include file="Footer.jsp"%>
	<script src="resources/js/ajax.js"></script>
</body>
</html>