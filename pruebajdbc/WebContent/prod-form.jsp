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
                        <c:if test="${producto != null}">
                            <form action="updateProd" method="post">
                        </c:if>
                        <c:if test="${producto == null}">
                            <form action="insertProd" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${producto != null}">
                                    Edit Product
                                </c:if>
                                <c:if test="${producto == null}">
                                    Add New Product
                                </c:if>
                            </h2>
                        </caption>
						
                        <c:if test="${producto != null}">
                            <input type="hidden" name="id" value="<c:out value='${producto.id}' />" />
                        </c:if>
                        
                         <fieldset class="form-group">
                            <label>Nombre producto</label> <input type="text" value="<c:out value='${producto.producto}' />" class="form-control" name="producto" required>
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Precio</label> <input type="text" value="<c:out value='${producto.precio}' />" class="form-control" name="precio" required>
                        </fieldset>
                        

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>