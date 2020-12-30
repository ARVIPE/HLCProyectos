<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.Equipo"%>
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

                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Escudo</th>
                            <th>Nombre</th>
                            <th>Puntos</th>
                            <th>Victorias</th>
                            <th>Empates</th>
                            <th>Derrotas</th>
                            <th>Goles a favor</th>
                            <th>Goles en contra</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <c:forEach var="equipo" items="${listEquip}">
                            <tr>
                                <td><c:out value="${equipo.id}" /></td>
                                <td><img src="<c:out value='images\\'/><c:out value='${equipo.id}' />.png" width="50px" height="50px"></td>
                                <td><c:out value="${equipo.nombre}" /></td>
                                <td><c:out value="${equipo.puntos}" /></td>
                                <td><c:out value="${equipo.v}" /></td>
                                <td><c:out value="${equipo.e}" /></td>
                                <td><c:out value="${equipo.d}" /></td>
                                <td><c:out value="${equipo.gf}" /></td>
                                <td><c:out value="${equipo.gc}" /></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>

                </table>
                
                <form action="loginServlet" method="post">
                    <input name="pass" type="password" placeholder="Contraseña"/>
                    <button class="btn btn-info">Administrador</button>
                </form>

            </div>

        </div>


    </body>
</html>
