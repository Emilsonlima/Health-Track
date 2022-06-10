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
					<h2>Editar Alimentos</h2>
					<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
				</div>
				<div class="panel-row">
					<div class="dynamic-content">
						<form action="alimento" method="post">
						<input type="hidden" value="editar" name="acao"> <input
									type="hidden" value="${alimento.id_ref }" name="id_ref">
							<div class="form-group">
								<label for="tp_ref" class="form-label">Tipo da Refeição</label>
								<input type="text" class="form-control" name="tp_ref"
									value="${alimento.tp_ref }" id="tp_ref" />
							</div>
							<div class="form-group">
								<label for="dt_ref" class="form-label">Data de Refeição</label>
								<input type="text" class="form-control" name="dt_ref"
									value='<fmt:formatDate value="${alimento.dt_ref.time }" pattern="dd/MM/yyyy"/>' id="dt_ref" />
							</div>
							<div class="form-group">
								<label for="nr_cal" class="form-label">Calorias
									Ingeridas</label> <input type="text" class="form-control" name="nr_cal"
									value="${alimento.nr_cal }" id="nr_cal" />
							</div>
							<p></p>
							<input type="submit" class="btn btn-primary" value="Cadastrar">
							<a href="alimento?acao=listar" class="btn btn-danger">Cancelar</a>
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