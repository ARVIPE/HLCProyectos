<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
       
   
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                    <!--  He importado la librería java server pages que me permite mostrar el arraylist de objetos así como condicionales y demás -->
                        <c:if test="${usuario != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${usuario == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${usuario != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${usuario == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>
						
                        <c:if test="${usuario != null}">
                            <input type="hidden" name="id" value="<c:out value='${usuario.id}' />" />
                        </c:if>
                        
                         <fieldset class="form-group">
                            <label>User Name</label> <input type="text" value="<c:out value='${usuario.usuario}' />" class="form-control" name="usuario" required>
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Contraseña</label> <input type="text" value="<c:out value='${usuario.contrasena}' />" class="form-control" name="contrasena" required>
                        </fieldset>
                        
                        
                        <fieldset class="form-group">
                            <label>Email</label> <input type="text" value="<c:out value='${usuario.email}' />" class="form-control" name="email" required>
                        </fieldset>
                     
                       	<fieldset class="form-group">
                            <label>Admin</label> <input type="text" value="<c:out value='${usuario.admin}' />" class="form-control" name="admin" required>
                        </fieldset>
                        

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>