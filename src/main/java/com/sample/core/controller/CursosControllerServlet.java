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
                "jdbc:mysql://localhost:3306/colegio", "root", ""
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
        int idProfesor = usuario.getId();
        System.out.println("[DEBUG] ID Profesor obtenido: " + idProfesor); // ✔️ Verifica ID

        try {
            Map<Curso, List<Materia>> cursosConMaterias = cursoService.obtenerCursosYMateriasPorProfesor(idProfesor);
            System.out.println("[DEBUG] Cursos encontrados: " + cursosConMaterias.size()); // ✔️ Verifica cantidad

            // Log detallado de cursos y materias
            cursosConMaterias.forEach((curso, materias) -> {
                System.out.println("Curso: " + curso.getNombre() + " | Materias: " + materias.size());
            });

            request.setAttribute("cursosConMaterias", cursosConMaterias);
            request.getRequestDispatcher("/DocenteCursos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los cursos.");
        }
    }  }