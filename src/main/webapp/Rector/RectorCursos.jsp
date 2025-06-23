<%@ page import="java.util.*, com.sample.core.domain.Curso, com.sample.core.domain.Materia" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cursos del Rector</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/cursosDocente.css">
</head>
<body>
    <div class="header">
        <img src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY" alt="Logo Colegio" class="logo">
        <h1 class="nombreColegio">Instituto Nuestra Señora de Fátima</h1>
    </div>
    
    <div class="user-bar">
        <div class="user-info">Bienvenido rector, <%= session.getAttribute("nombreUsuario") %></div>
        <a href="<%= request.getContextPath() %>/logout" class="logout-link">Cerrar sesión</a>
    </div>
    
    <div class="container">
        <h2>Cursos y Materias</h2>
        
        <%
        Map<Curso, List<Materia>> todosLosCursosConMaterias = (Map<Curso, List<Materia>>) request.getAttribute("todosLosCursosConMaterias");
        if (todosLosCursosConMaterias != null && !todosLosCursosConMaterias.isEmpty()) {
            for (Map.Entry<Curso, List<Materia>> entry : todosLosCursosConMaterias.entrySet()) {
                Curso curso = entry.getKey();
                List<Materia> materias = entry.getValue();
        %>
                <button class="acordeon">
                    <%= curso.getNombre() %>
                    <i class="fas fa-chevron-down"></i>
                </button>
                <div class="panel">
                    <% for (Materia materia : materias) { %>
                        <div class="materia-item">
                            <a href="<%= request.getContextPath() %>/notas?idMateria=<%= materia.getId() %>&idCurso=<%= curso.getId() %>">
                                - <%= materia.getNombre() %>
                            </a>
                        </div>
                    <% } %>
                </div>
        <%
            }
        } else {
        %>
            <div class="no-courses">
                <p>No hay cursos disponibles.</p>
            </div>
        <%
        }
        %>
    </div>

    <script>
        document.querySelectorAll('.acordeon').forEach(button => {
            button.nextElementSibling.style.display = 'none';
            
            button.addEventListener('click', function() {
                document.querySelectorAll('.panel').forEach(panel => {
                    if (panel !== this.nextElementSibling) {
                        panel.style.display = 'none';
                        panel.previousElementSibling.classList.remove('active');
                    }
                });
                
                this.classList.toggle('active');
                const panel = this.nextElementSibling;
                panel.style.display = panel.style.display === 'block' ? 'none' : 'block';
            });
        });
    </script>
</body>
</html>