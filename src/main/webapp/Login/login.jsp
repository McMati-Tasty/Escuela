<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <meta charset="UTF-8">
    <title>Login - Instituto Nuestra Señora de Fátima</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Formularios.css">
    <script src="${pageContext.request.contextPath}/scripts/jquery/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/login.js"></script>
</head><body>

  <div class="header">
    <img class="logo" src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY" alt="Logo">
    <h1 class="nombreColegio">Instituto Nuestra Señora de Fátima</h1>
  </div>

  <div class="container mt-4">
    <h1>Iniciar Sesión</h1>
    
    <div id="mensaje-ajax"></div>
    
    <form id="formulario-login">
      <div class="form-group">
        <label for="usuario">Usuario</label>
        <input class="form-control" type="text" name="txtUsuario" id="usuario" placeholder="Usuario" required>
      </div>
      
      <div class="form-group">
        <label for="clave">Contraseña</label>
        <input class="form-control" type="password" name="txtClave" id="clave" placeholder="Clave" required autofocus="1">
      </div>
      
      <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
    </form>

    <c:if test="${not empty error}">
      <div class="error">${error}</div>
    </c:if>
  </div>

  <!-- Comentario de depuración -->
  <script>
    console.log("Formulario de login cargado - " + new Date());
  </script>
</body>
</html>