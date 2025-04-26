<%--
  Created by IntelliJ IDEA.
  User: apiju
  Date: 23/04/2025
  Time: 15:03
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
<%@ page import="com.libdaw.models.Libro" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Librería DAW</title>
  <style><%@include file="/css/style.css"%></style>
</head>
<body>
<%
  if (session.getAttribute("status")==null){
    response.sendRedirect("index.jsp");
  }

  // Pagination logic
  int currentPage = request.getParameter("page") != null ?
          Integer.parseInt(request.getParameter("page")) : 1;
  int recordsPerPage = 4;
%>
<div class="container-list">
  <div class="user-container">
    <div class="user-display">
      <h1>Librería DAW</h1>
      <p class="active">Inicio</p>
      <div class="left-group">
        <span class="user-icon">👤</span>
        <span class="username"><%= request.getSession().getAttribute("username").toString().toUpperCase() %></span>
      </div>
    </div>
  </div>
  <p>
    <a href="libros?action=list">Libros</a>
    <a href="inventario?action=list">Inventario</a>
    <a href="logout">Cerrar sesión</a>
  </p>

  <div class="books-container">
    <%
      List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");

      if (listaLibros != null) {
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, listaLibros.size());

        for (int i = start; i < end; i++) {
          Libro item = listaLibros.get(i);
    %>
    <div class="book-card">
      <h2><%= item.getTitulo() %></h2>
      <p><span class="label">Autor:</span> <%= item.getAutor() %></p>
      <p><span class="label">Año:</span> <%= item.getPublicacion() %></p>
      <p><span class="label">Idioma:</span> <%= item.getIdioma() %></p>
      <p><span class="label">Páginas:</span> <%= item.getPaginas() %></p>
      <p><span class="label">Categoría:</span> <%= item.getCategoria() %></p>
      <p><span class="label">Precio:</span> <%= String.format("%.2f", item.getPrecio()) %> €</p>
      <p><span class="label">ISBN:</span> <%= item.getIsbn() %></p>
    </div>
    <%
        }
      }
    %>
  </div>

  <!-- Pagination -->
  <div class="pagination">
    <%
      if (listaLibros != null) {
        int totalPages = (int) Math.ceil((double) listaLibros.size() / recordsPerPage);

        if (currentPage > 1) {
    %>
    <a href="?page=<%= currentPage-1 %>">Anterior</a>
    <%
      }

      for (int i = 1; i <= totalPages; i++) {
        if (i == currentPage) {
    %>
    <a class="active" href="?page=<%= i %>"><%= i %></a>
    <%
    } else {
    %>
    <a href="?page=<%= i %>"><%= i %></a>
    <%
        }
      }

      if (currentPage < totalPages) {
    %>
    <a href="?page=<%= currentPage+1 %>">Siguiente</a>
    <%
        }
      }
    %>
  </div>
</div>
</body>
</html>
