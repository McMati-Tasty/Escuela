<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sample.core.domain.notas" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notas de Alumnos</title>
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

/* Título centrado */
h2 {
    text-align: center;
    color: #333;
    margin: 30px 0 20px 0;
    font-size: 28px;
    font-weight: 600;
}

/* Contenedor de la tabla */
table {
    width: 80%;
    margin: 0 auto 50px auto;
    border-collapse: collapse;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

/* Encabezado de la tabla */
th {
    background-color: #ff0000ea; /* rojo como acordeón */
    color: white;
    padding: 12px 15px;
    text-align: center;
    font-weight: 700;
    font-size: 16px;
}

/* Celdas de la tabla */
td {
    padding: 12px 15px;
    text-align: center;
    border-bottom: 1px solid #e0e0e0;
    font-size: 15px;
}

/* Filas pares con fondo distinto para mejor lectura */
tbody tr:nth-child(even) {
    background-color: #f8f9fa;
}

/* Mensaje cuando no hay datos */
td[colspan="6"] {
    padding: 20px;
    text-align: center;
    color: #721c24;
    background-color: #f8d7da;
    font-weight: 600;
    border-radius: 0 0 12px 12px;
}

/* Hover en filas para mejor interacción */
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
                            <td><%= n.getNota1() %></td>
                            <td><%= n.getNota2() %></td>
                            <td><%= n.getNota3() %></td>
                           <td><%= n.getPromedio() %></td>
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