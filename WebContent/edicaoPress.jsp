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
					<h2>Editar Pressão Arterial</h2>
					<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
				</div>
				<div class="panel-row">
					<div class="dynamic-content">
						<form action="press" method="post">
						<input type="hidden" value="editar" name="acao"> <input
									type="hidden" value="${press.id_press }" name="id_press">
							<div class="form-group">
								<label for="vl_press_sis" class="form-label">Pressão
									Sistólica</label> <input type="text" class="form-control"
									value="${press.vl_press_sis }" name="vl_press_sis" id="vl_press_sis"
									aria-describedby="emailHelp" />
							</div>
							<div class="form-group">
								<label for="vl_press_dis" class="form-label">Pressão
									Distólica</label> <input type="text" class="form-control"
									value="${press.vl_press_dis }" name="vl_press_dis" id="vl_press_dis" />
							</div>
							<div class="form-group">
								<label for="dt_press" class="form-label">Data da
									Aferição </label> <input type="text" class="form-control"
									value='<fmt:formatDate value="${press.dt_press.time }" pattern="dd/MM/yyyy"/>' name="dt_press" id="dt_press" />
							</div>
							<p></p>
							<input type="submit" class="btn btn-primary" value="Cadastrar">
							<a href="press?acao=listar" class="btn btn-danger">Cancelar</a>
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