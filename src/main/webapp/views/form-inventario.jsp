<%--
  Created by IntelliJ IDEA.
  User: apiju
  Date: 23/04/2025
  Time: 15:12
--%>
<%@ page import="com.libdaw.models.Inventario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  Inventario inventario = (Inventario) request.getAttribute("inventario");
  boolean isEdit = (inventario != null);
%>

<html>
<head>

  <title><%= isEdit ? "Editar Inventario" : "Nuevo Inventario" %>
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
  <h1><%= isEdit ? "Editar Inventario" : "Nuevo Inventario" %>
  </h1>
  <a href="inventario?action=list">Regresa a Inventario</a>
  <p style="color: red">Añadir en libro primero e indicar el ID del libro aquí</p>

  <form action="inventario" method="post">
    <input type="hidden" name="action" value="save"/>

    <% if (isEdit) { %>
    <input type="hidden" name="idInventario" value="<%= inventario.getIdInventario() %>"/>
    <% } %>

    <span>Fecha:</span>
    <input type="text" name="fecha"
           value="<%= isEdit ? inventario.getFecha() : "" %>" required/>

    <span>ID Libro:</span>
    <input type="text" name="idLibro"
           value="<%= isEdit ? inventario.getIdLibro() : "" %>" required/>

    <span>Titulo:</span>
    <input type="text" name="titulo"
           value="<%= isEdit ? inventario.getTitulo() : "" %>" required/>

    <span>Entrada:</span>
    <input type="text" name="entrada"
           value="<%= isEdit ? inventario.getEntrada() : "" %>" required/>

    <span>Salida:</span>
    <input type="text" name="salida"
           value="<%= isEdit ? inventario.getSalida() : "" %>" required/>

    <span>Stock:</span>
    <input type="text" name="stock"
           value="<%= isEdit ? inventario.getStock() : "" %>" />

    <button type="submit">Guardar</button>
  </form>
</div>
</body>
</html>

