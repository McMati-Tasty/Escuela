<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sample.core.domain.notas" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Notas de Alumnos</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/notasRector.css">


    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/notas.css">

    <script src="scripts/jquery/jquery-3.7.1.min.js"></script>
    <script src="scripts/EliminarAlumno.js"></script>
</head>
<body>

    <div class="header">
        <button class="back-button" onclick="history.back()">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                <path d="M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z"/>
            </svg>
        </button>
        <img src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY"
             alt="Logo Colegio" class="logo">
        <h1 class="nombreColegio">Instituto Nuestra Señora de Fátima</h1>
    </div>

    <h2>Notas de Alumnos</h2>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th class="col-nota">Nota 1</th>
                    <th class="col-nota">Nota 2</th>
                    <th class="col-nota">Nota 3</th>
                    <th class="col-promedio">Promedio</th>
                    <th class="col-acciones">Acciones</th>
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
                    <td class="col-nota"><%= n.getNota1() %></td>
                    <td class="col-nota"><%= n.getNota2() %></td>
                    <td class="col-nota"><%= n.getNota3() %></td>
                    <td class="col-promedio"><%= n.getPromedio() %></td>
                    <td class="col-acciones">
                        <button class="eliminar-btn"
                                data-id="<%= n.getIdAlumno() %>"
                                data-nombre="<%= n.getNombreAlumno() %>">
                          Eliminar
                        </button>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="7">No hay notas disponibles.</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

</body>
</html>
