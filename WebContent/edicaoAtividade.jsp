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
				<div class="main-content-title">
					<h2>Editar Atividades Fisicas</h2>
					<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
				</div>
				<div class="panel-row">
					<div class="dynamic-content">
						<form action="atividade" method="post">
						<input type="hidden" value="editar" name="acao"> <input
									type="hidden" value="${atividade.id_atividade }" name="id_atividade">
									
							<div class="form-group">
								<label for="nm_atividade" class="form-label">Modalidade
									da Atividade</label> <input type="text" class="form-control"
									value="${atividade.nm_atividade }" name= nm_atividade id="nm_atividade"  />
							</div>
							
							<div class="form-group">
								<label for="nr_calorias" class="form-label">Calorias Gastas
									</label> <input type="text" class="form-control"
									value="${atividade.nr_calorias  }" name= nr_calorias id="nr_calorias" />
							</div>
							<div class="form-group">
								<label for="dt_atividade" class="form-label">Data da Atividade</label> <input
									type="text" class="form-control"  value='<fmt:formatDate value="${atividade.dt_atividade.time }" pattern="dd/MM/yyyy"/>' name= "dt_atividade" id="dt_atividade" />
							</div>
							<p></p>
							<input type="submit" class="btn btn-primary" value="Cadastrar"></input>
							<a href="atividade?acao=listar" class="btn btn-danger">Cancelar</a>

						</form>
					</div>
				</div>
				<div class="content" id="ajax-content"></div>
			</div>
		</main>
	</div>

	<%@ include file="Footer.jsp"%>
</body>
</html>