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
				</a> <a href="index.jsp"> <i class="fas fa-sign-out-alt"></i> Logout
				</a>
			</header>
			<div class="main-content">

				<div class="container">
					<div class="main-content-title">
						<h2>Histórico de aferições</h2>
					</div>

					<table class="table table-striped">
						<tr>
							<th>Pressão Sistólica (mmhg)</th>
							<th>Pressão Sistólica (mmhg)</th>
							<th>Data</th>
							<th></th>

						</tr>
						<c:forEach items="${pressoes}" var="p">
							<tr>
								<td>${p.vl_press_sis}</td>
								<td>${p.vl_press_dis}</td>
								<td><fmt:formatDate value="${p.dt_press.time }"
										pattern="dd/MM/yyyy" /></td>
								<td><c:url value="peso" var="link">
										<c:param name="acao" value="abrir-form-edicao" />
										<c:param name="id_press" value="${p.id_press}" />
									</c:url> <a href="${link}">Editar</a>
									<button type="button" class="btn btn-danger btn-xs"
										data-toggle="modal" data-target="#excluirModal"
										onclick="codigoExcluir.value = ${p.id_press}">Excluir</button>
								</td>
							</tr>
						</c:forEach>
					</table>

				</div>
			</div>
		</main>
	</div>

	<%@ include file="Footer.jsp"%>
	<!-- Modal -->
	<div class="modal fade" id="excluirModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Deseja realmente excluir o produto?</div>
				<div class="modal-footer">
					<form action="press" method="post">
						<input type="hidden" name="acao" value="excluir"> <input
							type="hidden" name="id_press" id="codigoExcluir">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-danger">Excluir</button>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>