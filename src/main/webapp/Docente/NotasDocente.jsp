<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sample.core.domain.notas"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Notas de Alumnos</title>
<link rel="stylesheet" href="css/notas.css">
<script src="scripts/jquery/jquery-3.7.1.min.js"></script>
<script src="scripts/eliminarNotas.js"></script>
<script src="scripts/guardarNotas.js"></script>
</head>
<body>
	<div class="header">
		<button class="back-button" onclick="history.back()">
			<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                <path
					d="M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z" />
            </svg>
		</button>
		<img
			src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY"
			alt="Logo Colegio" class="logo">
		<h1 class="nombreColegio">Instituto Nuestra Señora de Fatima</h1>
	</div>

	<div class="container">
		<h2>Notas de Alumnos</h2>

		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th class="col-nombre">Nombre</th>
						<th class="col-apellido">Apellido</th>
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
						<td><%=n.getNombreAlumno()%></td>
						<td><%=n.getApellidoAlumno()%></td>
						<td><input type="number" class="nota1"
							value="<%=n.getNota1()%>" min="0" max="10" step="1"
							oninput="if (this.value==='') return;
             this.value = Math.max(0, Math.min(10, Number(this.value)));
           " /></td>
						<td><input type="number" class="nota2"
							value="<%=n.getNota2()%>" min="0" max="10" step="1"
							oninput="
             if (this.value==='') return;
             this.value = Math.max(0, Math.min(10, Number(this.value)));
           " /></td>
						<td><input type="number" class="nota3"
							value="<%=n.getNota3()%>" min="0" max="10" step="1"
							oninput="
             if (this.value==='') return;
             this.value = Math.max(0, Math.min(10, Number(this.value)));
           " /></td>

						<td class="promedio-cell"><span class="promedio-value"><%=n.getPromedio()%></span>
						</td>
						<td class="acciones-cell">
							<div class="acciones-container">
								<button class="guardarBtn" data-id="<%=n.getIdAlumno()%>">Guardar</button>
								<button class="eliminar-btn" data-id="<%=n.getIdAlumno()%>">Eliminar</button>
							</div>
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
	</div>
</body>
</html>