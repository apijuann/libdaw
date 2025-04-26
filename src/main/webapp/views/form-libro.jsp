<%--
  Created by IntelliJ IDEA.
  User: apiju
  Date: 23/04/2025
  Time: 15:10
--%>
<%@ page import="com.libdaw.models.Libro" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  Libro libros = (Libro) request.getAttribute("libros");
  boolean isEdit = (libros != null);
%>

<html>
<head>

  <title><%= isEdit ? "Editar Libro" : "Nuevo Libro" %>
  </title>
  <style>
    <%@include file="/css/style.css" %>
  </style>

</head>
<body>
<%
  if (session.getAttribute("status") == null) {
    response.sendRedirect("index.jsp");
  }
%>
<div class="container-form">
  <h1><%= isEdit ? "Editar Libro" : "Nuevo Libro" %>
  </h1>
  <a href="libros?action=list">Regresa a Libros</a>

  <form action="libros" method="post">
    <input type="hidden" name="action" value="save"/>

    <% if (isEdit) { %>
    <input type="hidden" name="idLibro" value="<%= libros.getIdLibro() %>"/>
    <% } %>

    <span>Titulo:</span>
    <input type="text" name="titulo"
           value="<%= isEdit ? libros.getTitulo() : "" %>" required/>

    <span>Autor:</span>
    <input type="text" name="autor"
           value="<%= isEdit ? libros.getAutor() : "" %>" />

    <span>Año de publicacion:</span>
    <input type="text" name="publicacion"
           value="<%= isEdit ? libros.getPublicacion() : "" %>" />

    <span>Idioma:</span>
    <input type="text" name="idioma"
           value="<%= isEdit ? libros.getIdioma() : "" %>" />

    <span>Paginas:</span>
    <input type="text" name="paginas"
           value="<%= isEdit ? libros.getPaginas() : "" %>" required/>

    <span>Categoria:</span>
    <input type="text" name="categoria"
           value="<%= isEdit ? libros.getCategoria() : "" %>" />

    <span>Precio:</span>
    <input type="text" name="precio"
           value="<%= isEdit ? libros.getPrecio() : "" %>" required/>

    <span>ISBN:</span>
    <input type="text" name="isbn"
           value="<%= isEdit ? libros.getIsbn() : "" %>" />

    <button type="submit">Guardar</button>
  </form>
</div>
</body>
</html>

