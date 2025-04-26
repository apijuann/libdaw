<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>LibDaw | Proyecto Inventario</title>
  <style><%@include file="css/style.css"%></style>
</head>
<body>
<h1><%= "LibDaw" %></h1>
<h3><%= "Proyecto Inventario" %></h3>
<h5><%= "Instituto Nebrija. 1º DAW" %></h5>

<form action="login" method="post">
  <label><b>ACCEDE</b> A LA LIBRERIA</label>
  <p></p>
  Tu nombre de usuario: <input type="text" name="username" autocomplete="off">
  Tu contraseña: <input type="password" name="password">
  <input type="submit" name="submit" value="Acceder">

  <%-- Error message --%>
  <% if (request.getAttribute("error") != null) { %>
  <div class="error-message">
    <%= request.getAttribute("error") %>
  </div>
  <% } %>
</form>

<h5><%= "@2024-2025 APIJUANN | Todos los derechos reservados." %></h5>

</body>
</html>