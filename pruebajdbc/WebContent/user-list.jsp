<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="proyecto.model.Usuario"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	
	<div class="row">
	<% if((session.getAttribute("logueado")) == null){
		response.sendRedirect("login.jsp");
	}else{
	
	%>

		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Listado de usuarios</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Añadir nuevo usuario</a>
				<a href="loginsuccessAdmin.jsp" class="btn btn-success">Volver</a>
			</div>
			
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th> 
						<th>Contraseña</th>
						<th>Email</th>
						<th>Administrador</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="usuario" items="${listUser}">
						<tr>
							<td><c:out value="${usuario.id}" /></td>
							<td><c:out value="${usuario.usuario}" /></td>
							<td><c:out value="${usuario.contrasena}" /></td>
							<td><c:out value="${usuario.email}" /></td>
							<td><c:out value="${usuario.admin}" /></td>
							<td><a href="edit?id=<c:out value='${usuario.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${usuario.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
		 <
	</div>
	
	<%
	}
	

	%>
</body>
</html>
