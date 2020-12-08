<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% out.print("<h3>Precio Final " + session.getAttribute("precioFinal") + "</h3>"); %>
<a href="loginsuccess.jsp" class="btn btn-success">Volver</a>
</body>
</html>