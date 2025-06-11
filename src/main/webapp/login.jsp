<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Formulario</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Formularios.css">
</head>
<body>

  <div class="header">
    <img class="logo" src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY" alt="Logo" width="100" height="auto">
    <h1 class="nombreColegio">Instituto Nuestra Seniora de Fatima</h1>
  </div>


  <div class="container mt-4">
    <h1>Iniciar Sesion</h1>
    

<form action="${pageContext.request.contextPath}/login" method="POST">

    
      <div class="form-group">
        <label for="usuario">Usuario</label>
        <input class="form-control" type="text" name="txtUsuario" id="usuario" placeholder="Usuario" required>
      </div>
      
      <div class="form-group">
        <label for="clave">Contraseña</label>
        <input class="form-control" type="password" name="txtClave" id="clave" placeholder="Clave" required autofocus="1">
      </div>
      
      <input class="btn btn-primary" type="submit" name="accion" value="Iniciar Sesión">
      
    </form>

   
    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>
  </div>

</body>
</html>


