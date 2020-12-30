<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.Equipo"%>
<%@page import="model.Partido"%>
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

                <h3>Hola Admin!!!</h3>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Escudo</th>
                            <th>Equipo Local</th>
                            <th>Goles Local</th>
                            <th>Goles Visitante</th>
                            <th>Equipo Visitante</th>
                            <th>Escudo</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <c:forEach var="partido" items="${listMatch}">
                            <tr>
                                <td><c:out value="${partido.id}" /></td>
                                <td><img src="<c:out value='images\\'/><c:out value='${partido.e1}' />.png" width="50px" height="50px"></td>
                                <td><c:out value="${partido.e1}" /></td>
                                <td><c:out value="${partido.g1}" /></td>
                                <td><c:out value="${partido.g2}" /></td>
                                <td><c:out value="${partido.e2}" /></td>
                                <td><img src="<c:out value='images\\'/><c:out value='${partido.e2}' />.png" width="50px" height="50px"></td>
                                <td><a href="edit?id=<c:out value='${partido.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="delete?id=<c:out value='${partido.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->


                    </tbody>
                </table>





                <form action="insert" method="post">
                    <select name="e1">
                        <c:forEach var="equipo" items="${listEquip}">
                            <option>
                                <c:out value="${equipo.nombre}" />
                            </option>
                        </c:forEach>
                    </select>

                    
                    <select name="g1">

                        <c:forEach begin="0" step="1" end="10" var="variable">
                            <option>
                                  <c:out value="${variable}" />
                                </option>
                        </c:forEach>

                    </select>
                    
                    <select name="g2">

                        <c:forEach begin="0" step="1" end="10" var="variable">
                            <option> bn
                                <c:out value="${variable}"></c:out>
                                </option>
                        </c:forEach>

                    </select>

                    <select name="e2">
                        <c:forEach var="equipo" items="${listEquip}">
                            <option>
                                <c:out value="${equipo.nombre}" />
                            </option>
                        </c:forEach>
                    </select>

                    <button class="btn btn-info">Insertar</button>
                </form>
            </div>

        </div>






    </body>
</html>
