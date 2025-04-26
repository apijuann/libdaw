<%--
  Created by IntelliJ IDEA.
  User: apiju
  Date: 23/04/2025
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<!-- to close session -->
<%
    HttpSession sessionCheck = request.getSession(false);
    if (sessionCheck == null || sessionCheck.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!-- end session -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.libdaw.models.Inventario" %>

<html>
<head>
    <title>Gestión de Inventario</title>
    <style><%@include file="/css/style.css"%></style>
</head>
<body>
<%
    if (session.getAttribute("status")==null){
        response.sendRedirect("index.jsp");
    }

    // Pagination parameters
    int rowsPerPage = 4;
    int currentPage = 1;
    String pageParam = request.getParameter("page");
    if (pageParam != null && !pageParam.isEmpty()) {
        currentPage = Integer.parseInt(pageParam);
    }
%>
<div class="container-list">
    <div class="user-container">
        <div class="user-display">
            <h1>Inventario</h1>
            <p class="active">Gestión de Inventario</p>
            <div class="left-group">
                <span class="user-icon">👤</span>
                <span class="username"><%= request.getSession().getAttribute("username").toString().toUpperCase() %></span>
            </div>
        </div>
    </div>
    <p>
        <a href="inventario?action=new">Añadir inventario</a>
        <a href="libros?action=list">Libros</a>
        <a href="lista?action=list">Librería DAW</a>
        <a href="logout">Cerrar sesión</a>
    </p>
    <table>
        <tr>
            <th>ID Inventario</th>
            <th>Fecha</th>
            <th>ID Libro</th>
            <th>Titulo</th>
            <th>Entrada</th>
            <th>Salida</th>
            <th>Stock</th>
            <th>Acción</th>
        </tr>
        <%
            List<Inventario> listaInventarios = (List<Inventario>) request.getAttribute("listaInventarios");
            int totalStock = 0;
            int pageStock = 0;
            int startIndex = (currentPage - 1) * rowsPerPage;
            int endIndex = Math.min(startIndex + rowsPerPage, listaInventarios != null ? listaInventarios.size() : 0);

            if (listaInventarios != null) {
                // Calculate total stock for all pages
                for (Inventario item : listaInventarios) {
                    totalStock += item.getStock();
                }

                // Display items for current page
                for (int i = startIndex; i < endIndex; i++) {
                    Inventario item = listaInventarios.get(i);
                    pageStock += item.getStock();
        %>
        <tr>
            <td><%= item.getIdInventario() %></td>
            <td><%= item.getFecha() %></td>
            <td><%= item.getIdLibro() %></td>
            <td><%= item.getTitulo() %></td>
            <td><%= item.getEntrada() %></td>
            <td><%= item.getSalida() %></td>
            <td><%= item.getStock() %></td>
            <td>
                <a href="inventario?action=edit&id=<%= item.getIdInventario() %>">Editar</a>
                <a href="delete-inventario?id=<%= item.getIdInventario() %>">
                    Eliminar
                </a>
            </td>
        </tr>
        <%
                }
            }
        %>
        <!-- Totals row for current page -->
        <tr class="totals-row">
            <td colspan="6">STOCK PÁGINA ACTUAL</td>
            <td><%= pageStock %></td>
            <td colspan="2"></td>
        </tr>
        <!-- Grand totals row -->
        <tr class="grand-totals-row">
            <td colspan="6">TOTAL STOCK</td>
            <td><%= totalStock %> en stock</td>
            <td colspan="2"></td>
        </tr>
    </table>

    <!-- Pagination controls -->
    <div class="pagination">
        <%
            if (listaInventarios != null) {
                int totalPages = (int) Math.ceil((double) listaInventarios.size() / rowsPerPage);

                if (currentPage > 1) {
        %>
        <a href="?page=<%= currentPage - 1 %>">Anterior</a>
        <%
            }

            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
        %>
        <a class="active"><%= i %></a>
        <%
        } else {
        %>
        <a href="?page=<%= i %>"><%= i %></a>
        <%
                }
            }

            if (currentPage < totalPages) {
        %>
        <a href="?page=<%= currentPage + 1 %>">Siguiente</a>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
