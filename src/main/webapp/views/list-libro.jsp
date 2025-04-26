<%--
  Created by IntelliJ IDEA.
  User: apiju
  Date: 23/04/2025
  Time: 15:06
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
<%@ page import="com.libdaw.models.Libro" %>

<html>
<head>
  <title>Gestión de libros</title>
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
      <h1>Libros</h1>
      <p class="active">Gestión de Libros</p>
      <div class="left-group">
        <span class="user-icon">👤</span>
        <span class="username"><%= request.getSession().getAttribute("username").toString().toUpperCase() %></span>
      </div>
    </div>
  </div>
  <p>
    <a href="libros?action=new">Añadir libro</a>
    <a href="inventario?action=list">Inventario</a>
    <a href="lista?action=list">Librería DAW</a>
    <a href="logout">Cerrar sesión</a>
  </p>
  <table>
    <tr>
      <th>ID Libro</th>
      <th>Titulo</th>
      <th>Autor</th>
      <th>Año de publicación</th>
      <th>Idioma</th>
      <th>Páginas</th>
      <th>Categoría</th>
      <th>Precio</th>
      <th>ISBN</th>
      <th>Acción</th>
    </tr>
    <%
      List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");
      int totalLibro = 0;
      int totalRecords = 0;
      int pageRecords = 0;
      int startIndex = (currentPage - 1) * rowsPerPage;
      int endIndex = Math.min(startIndex + rowsPerPage, listaLibros != null ? listaLibros.size() : 0);

      if (listaLibros != null) {
        // Calculate totals for all pages
        totalRecords = listaLibros.size();
        for (Libro item : listaLibros) {
          totalLibro++;
        }

        // Display items for current page and calculate page totals
        for (int i = startIndex; i < endIndex; i++) {
          Libro item = listaLibros.get(i);
          pageRecords++;
    %>
    <tr>
      <td><%= item.getIdLibro() %></td>
      <td><%= item.getTitulo() %></td>
      <td><%= item.getAutor() %></td>
      <td><%= item.getPublicacion() %></td>
      <td><%= item.getIdioma() %></td>
      <td><%= item.getPaginas() %></td>
      <td><%= item.getCategoria() %></td>
      <td><%= item.getPrecio() %></td>
      <td><%= item.getIsbn() %></td>
      <td>
        <a href="libros?action=edit&id=<%= item.getIdLibro() %>">Editar</a>
        <a href="delete?id=<%= item.getIdLibro() %>">
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
      <td colspan="2">REGISTROS PÁGINA ACTUAL</td>
      <td><%= pageRecords %></td>
      <td colspan="7"></td>
    </tr>
    <!-- Grand totals row -->
    <tr class="grand-totals-row">
      <td colspan="2">TOTAL REGISTROS</td>
      <td><%= totalRecords %> registros</td>
      <td colspan="7"></td>
    </tr>
  </table>

  <!-- Pagination controls -->
  <div class="pagination">
    <%
      if (listaLibros != null) {
        int totalPages = (int) Math.ceil((double) listaLibros.size() / rowsPerPage);

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
