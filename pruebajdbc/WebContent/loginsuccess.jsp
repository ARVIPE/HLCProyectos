<%-- 
    Document   : loginsuccessAdmin
    Created on : 30 nov. 2020, 21:13:17
    Author     : arvip
--%>

<%@page import="proyecto.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <style>
    body{
    
    background-color: #61ff71;
    
    }
    
    </style>
    <body>
        <h1>Vista Usuario</h1>
 
      	<%     	
      		out.println("<h5>Hola  " + session.getAttribute("email") + "</h5> ");
        %>
       
        
        <div class="container text-left">
				<a href="<%=request.getContextPath()%>/listProdNormal" class="btn btn-success">Listado Productos</a>
				<a href="/pruebajdbc/salir" class="btn btn-success">Salir</a>
			</div>
        

    </body>
</html>
