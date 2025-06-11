<%@ page import="java.util.*, com.sample.core.domain.Curso, com.sample.core.domain.Materia" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Cursos</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
        }
        
        /* Logo y header */
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
        
        /* Contenedor principal */
        .container {
            max-width: 800px;
            margin: 30px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        
        /* Títulos */
        h1, h2 {
            color: #333;
            margin-bottom: 20px;
        }
        
        /* Barra de usuario */
        .user-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 30px;
            background-color: #f8f9fa;
            border-bottom: 1px solid #e9ecef;
        }
        
        .user-info {
            font-size: 16px;
            color: #333;
        }
        
        .logout-link {
            color: #ff0000ea;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        
        .logout-link:hover {
            color: #b30600;
            text-decoration: underline;
        }
        
        /* Acordeón de cursos */
        .acordeon {
            width: 100%;
            padding: 15px;
            border: none;
            text-align: left;
            font-size: 16px;
            margin: 8px 0;
            border-radius: 8px;
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #ff0000ea;
            color: white;
            transition: background-color 0.3s ease;
        }
        
        .acordeon:hover {
            background-color: #b30600;
        }
        
        .acordeon i {
            margin-left: 10px;
            transition: transform 0.3s ease;
        }
        
        .acordeon.active i {
            transform: rotate(180deg);
        }
        
        .panel {
            padding: 0 20px;
            display: none;
            background-color: #f8f9fa;
            border-left: 4px solid #ff0000ea;
            border-radius: 0 0 8px 8px;
            margin-bottom: 15px;
        }
        
        .materia-item {
            padding: 12px;
            border-bottom: 1px solid #e9ecef;
            font-size: 15px;
            color: #555;
        }
        
        .no-courses {
            padding: 20px;
            text-align: center;
            background-color: #f8d7da;
            color: #721c24;
            border-radius: 8px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="header">
        <img src="https://media.licdn.com/dms/image/v2/C4E0BAQG160jzL6-JqQ/company-logo_200_200/company-logo_200_200/0/1630600241937?e=2147483647&v=beta&t=79lDsncCWhCvwrjJzii6eOau3_IAdsIR1xH7r_7PbVY" alt="Logo Colegio" class="logo">
        <h1 class="nombreColegio">Instituto Nuestra Seniora de Fatima</h1>
    </div>
    
    <div class="user-bar">
        <div class="user-info">Bienvenido profesor, <%= session.getAttribute("nombreUsuario") %></div>
        <a href="<%= request.getContextPath() %>/logout" class="logout-link">Cerrar sesion</a>
    </div>
    
    <div class="container">
        <h2>Mis Cursos</h2>
        
        <%
        Map<Curso, List<Materia>> cursosConMaterias = (Map<Curso, List<Materia>>) request.getAttribute("cursosConMaterias");
        if (cursosConMaterias != null && !cursosConMaterias.isEmpty()) {
            for (Map.Entry<Curso, List<Materia>> entry : cursosConMaterias.entrySet()) {
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
                <p>No tienes cursos asignados.</p>
            </div>
        <%
        }
        %>
    </div>

    <script>
        document.querySelectorAll('.acordeon').forEach(button => {
            // Configurar todos los acordeones como cerrados inicialmente
            button.nextElementSibling.style.display = 'none';
            
            button.addEventListener('click', function() {
                // Cerrar otros acordeones al abrir uno nuevo (opcional)
                document.querySelectorAll('.panel').forEach(panel => {
                    if (panel !== this.nextElementSibling) {
                        panel.style.display = 'none';
                        panel.previousElementSibling.classList.remove('active');
                    }
                });
                
                // Alternar el acordeón clickeado
                this.classList.toggle('active');
                const panel = this.nextElementSibling;
                panel.style.display = panel.style.display === 'block' ? 'none' : 'block';
            });
        });
    </script>
</body>
</html>