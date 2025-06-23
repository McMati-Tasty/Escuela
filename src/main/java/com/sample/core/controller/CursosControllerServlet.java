package com.sample.core.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.core.dao.CursoDaoImp;
import com.sample.core.domain.Curso;
import com.sample.core.domain.Materia;
import com.sample.core.domain.Usuarios;
import com.sample.core.service.CursoService;
import com.sample.core.service.CursoServiceImpl;

@WebServlet("/cursos")
public class CursosControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CursoService cursoService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/colegio?serverTimezone=UTC", "root", ""
            );
            CursoDaoImp cursoDao = new CursoDaoImp(connection);
            cursoService = new CursoServiceImpl(cursoDao);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar el servicio de cursos", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");
        String rol = usuario.getRol();
        System.out.println("[DEBUG] Usuario con rol: " + rol);

        try {
            if ("profesor".equalsIgnoreCase(rol)) {
                // Para profesor, obtener solo sus cursos y materias
                int idProfesor = usuario.getId();
                Map<Curso, List<Materia>> cursosConMaterias = cursoService.obtenerCursosYMateriasPorProfesor(idProfesor);
                request.setAttribute("cursosConMaterias", cursosConMaterias);
                request.getRequestDispatcher("/Docente/DocenteCursos.jsp").forward(request, response);
            } else if ("rector".equalsIgnoreCase(rol)) {
                // Para rector, obtener todos los cursos y materias
            	int idRector = usuario.getId();
                Map<Curso, List<Materia>> todosLosCursosConMaterias = cursoService.obtenerTodosLosCursosYMaterias(idRector);
                request.setAttribute("todosLosCursosConMaterias", todosLosCursosConMaterias);
                request.getRequestDispatcher("/Rector/RectorCursos.jsp").forward(request, response);

            } else {
                // Rol no autorizado, redirigir a login
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los cursos.");
        }
    }
}