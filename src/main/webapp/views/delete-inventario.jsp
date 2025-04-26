<%--
  Created by IntelliJ IDEA.
  User: apiju
  Date: 24/04/2025
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String id = request.getParameter("id"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar libro</title>
    <style><%@include file="/css/style.css"%></style>
</head>
<body>
<div class="delete-confirm-container">
    <div class="delete-confirm-card">
        <div class="delete-header">
            <h2>Eliminar Inventario</h2>
        </div>
        <div class="delete-message">
            <p>¿Vas a eliminar este inventario?</p>
            <p class="book-id">Id del inventario: <%= id %></p>
        </div>
        <div class="delete-actions">
            <a href="inventario?action=list" class="cancel-btn">Cancelar</a>
            <a href="inventario?action=delete&id=<%= id %>" class="confirm-btn">Sí, Eliminar</a>
        </div>
    </div></div>
</body>
</html>

