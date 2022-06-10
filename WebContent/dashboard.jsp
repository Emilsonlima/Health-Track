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
				<a href="dashboard.jsp"> <i class="fas fa-home"></i> Dashboard
				</a> <a href="index.jsp"> <i class="fas fa-sign-out-alt"></i> Logout
				</a>
			</header>
			<div class="main-content">
				<div class="main-content-tittle">
					<h2>DASHBOARD</h2>
				</div>
				<div class="dashboard-content">
					<div class="dashboard-flex-parent">
						<div class="dashboard-box">
							<form>
								<div class="dashboard-box-wrapper">
									<div class="box-icon box-weight">
										<i class="fas fa-balance-scale-right"> </i>
									</div>
									<p></p>
									<input type="text" name="vl_peso" value="${peso.vl_peso }"
										id="vl_peso" />
									<div class="type">KG</div>


								</div>
							</form>
						</div>

						<div class="dashboard-box">
							<form>
								<div class="dashboard-box-wrapper">
									<div class="box-icon box-pressure">
										<i class="fas fa-heartbeat"> </i>
									</div>
									<p></p>
									<input type="text" value="${press.vl_press_sis }"
										name="vl_press_sis" id="vl_press_sis" />
									<div class="type">Pressão sistólica</div>

								</div>
							</form>

						</div>


						<div class="dashboard-box">
							<form action="">
								<div class="dashboard-box-wrapper">
									<div class="box-icon box-pressure">
										<i class="fas fa-heartbeat"> </i>
									</div>
									<p></p>
									<input type="text" value="${press.vl_press_dis }"
										name="vl_press_dis" id="vl_press_dis" />
									<div class="type">Pressão distólica</div>

								</div>
							</form>
						</div>



						<div class="dashboard-box">
							<form action="">
								<div class="dashboard-box-wrapper">
									<div class="box-icon box-workout">
										<i class="fas fa-running"></i>
									</div>
									<p></p>
									<input type="text"
										value="${atividade.nm_atividade }" name=nm_atividade
										id="nm_atividade" />
									<div class="type">Atividade Fisica</div>									
								</div>
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