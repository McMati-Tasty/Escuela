<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sample.core.domain.notas" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notas de Alumnos</title>
    <script src="scripts/jquery/jquery-3.7.1.min.js"></script>
	<script src="scripts/guardarNotas.js"></script>
    <style>
       body {
           font-family: Arial, sans-serif;
           background-color: #ffffff;
           margin: 0;
           padding: 0;
           color: #333;
       }
       .header {
           display: flex;
           align-items: center;
           gap: 20px;
           padding: 20px;
           margin-left: 20px;
       }
       .logo {
           width: 100px;
           height: auto;
       }
       .nombreColegio {
           font-size: 24px;
           color: #333;
           margin: 0;
       }
       h2 {
           text-align: center;
           color: #333;
           margin: 30px 0 20px 0;
           font-size: 28px;
           font-weight: 600;
       }
       table {
           width: 80%;
           margin: 0 auto 50px auto;
           border-collapse: collapse;
           border-radius: 12px;
           box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
           overflow: hidden;
       }
       th {
           background-color: #ff0000ea;
           color: white;
           padding: 12px 15px;
           text-align: center;
           font-weight: 700;
           font-size: 16px;
       }
       td {
           padding: 12px 15px;
           text-align: center;
           border-bottom: 1px solid #e0e0e0;
           font-size: 15px;
       }
       tbody tr:nth-child(even) {
           background-color: #f8f9fa;
       }
       td[colspan="6"] {
           padding: 20px;
           text-align: center;
           color: #721c24;
           background-color: #f8d7da;
           font-weight: 600;
           border-radius: 0 0 12px 12px;
       }
       tbody tr:hover {
           background-color: #ffeaea;
           cursor: default;
       }
    </style>
</head>
<body>

    <div class="header">
        <img src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY" alt="Logo Colegio" class="logo">
        <h1 class="nombreColegio">Instituto Nuestra Seniora de Fatima</h1>
    </div>

    <h2>Notas de Alumnos</h2>

    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Nota 1</th>
                <th>Nota 2</th>
                <th>Nota 3</th>
                <th>Promedio</th>
            </tr>
        </thead>
<tbody>
<%
    List<notas> lista = (List<notas>) request.getAttribute("notas");
    if (lista != null && !lista.isEmpty()) {
        for (notas n : lista) {
%>
            <tr>
                <td><%= n.getNombreAlumno() %></td>
                <td><%= n.getApellidoAlumno() %></td>
				<td><input type="number" class="nota1" value="<%= n.getNota1() %>" /></td>
				<td><input type="number" class="nota2" value="<%= n.getNota2() %>" /></td>
				<td><input type="number" class="nota3" value="<%= n.getNota3() %>" /></td>
			<td>
    			<%= n.getPromedio() %>
    			<button class="guardarBtn" data-id="<%= n.getIdAlumno() %>">Guardar</button>
</td>

            </tr>
<%
        }
    } else {
%>
        <tr>
            <td colspan="6">No hay notas disponibles.</td>
        </tr>
<%
    }
%>
</tbody>
    </table>
</body>
</html>