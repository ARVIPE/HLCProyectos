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


		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Listado de productos</h3>
			<hr>
			<div class="container text-left">


				<a href="loginsuccess.jsp" class="btn btn-success">Volver</a>
				<a href="limpiarCesta" class="btn btn-success">LimpiarCesta</a>
				<% out.print("<h3>Precio Final " + session.getAttribute("precioFinal") + "</h3>"); %>
				
			</div>
			
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Producto</th>
						<th>Precio</th> 
						<th></th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="producto" items="${listProd}">
						<tr>
							<td><c:out value="${producto.id}" /></td>
							<td><c:out value="${producto.producto}" /></td>
							<td><c:out value="${producto.precio}" /></td>
							<td><a href="agregarProd?id=<c:out value='${producto.id}' />">Agregar</a></td>
						</tr>
					</c:forEach>
					
					
					<!-- } -->
				</tbody>

			</table>
		</div>
		 
	</div>
</body>
</html>
