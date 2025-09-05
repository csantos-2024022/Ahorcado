<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Palabras</title>
        <link rel="stylesheet" href="css/palabras.css">
    </head>
    <body>
        <h1>Administrar Palabras</h1>
        
        <h2>Agregar Nueva Palabra</h2>
        <form action="Controlador?menu=Palabras&accion=Agregar" method="post">
            <label for="txtPalabra">Palabra:</label>
            <input type="text" id="txtPalabra" name="txtPalabra" required>
            <button type="submit">Agregar</button>
            <label for="txtPista">Pista</label>
            <input type="text" id="txtPista" name="txtPista" required>
        </form>
        
        <hr>
        
        <h2>Lista de Palabras</h2>
        <table>
            <thead>
                <tr>
                    <th>codigo</th>
                    <th>Palabra</th>
                    <th>pista</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${listaPalabras}">
                    <tr>
                        <td>${p.getCodigoPalabra()}</td>
                        <td>${p.getPalabra()}</td>
                        <td>${p.getPista()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <br>
        <a href="Controlador?menu=Principal">Volver al Inicio</a>
    </body>
</html>