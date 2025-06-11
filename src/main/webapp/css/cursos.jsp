<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cursos de Carlos</title>
</head>
<body>
    <h1>Cursos de Carlos</h1>
    <ul>
        <c:forEach var="curso" items="${cursos}">
            <li>${curso.nombre}</li>
        </c:forEach>
    </ul>
</body>
</html>
