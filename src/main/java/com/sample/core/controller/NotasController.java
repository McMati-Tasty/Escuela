package com.sample.core.controller;

import com.sample.core.dao.NotasDao;
import com.sample.core.dao.NotasDaoImp;
import com.sample.core.domain.notas;
import com.sample.core.domain.Usuarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/notas")
public class NotasController extends HttpServlet {

    private NotasDao notasDao;

    @Override
    public void init() throws ServletException {	
        this.notasDao = new NotasDaoImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");
            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String idMateriaParam = request.getParameter("idMateria");
            String idCursoParam = request.getParameter("idCurso");

            if (idMateriaParam == null || idCursoParam == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros idMateria o idCurso");
                return;
            }

            int idMateria = Integer.parseInt(idMateriaParam);
            int idCurso = Integer.parseInt(idCursoParam);

            List<notas> listaNotas;

            // Detectamos si es rector o profesor
            if ("rector".equalsIgnoreCase(usuario.getRol())) {
                // No filtramos por profesor
                listaNotas = notasDao.obtenerNotasParaRector(idMateria, idCurso);
            } else {
                // Profesor: filtramos por su ID
                int idProfesor = usuario.getId();
                listaNotas = notasDao.obtenerNotasConPromedio(idProfesor, idMateria, idCurso);
                session.setAttribute("idProfesor", idProfesor); // solo si lo vas a necesitar
            }

            session.setAttribute("idMateria", idMateria);
            session.setAttribute("idCurso", idCurso);

            request.setAttribute("notas", listaNotas);
            if ("rector".equalsIgnoreCase(usuario.getRol())) {
                request.getRequestDispatcher("/Rector/NotasRector.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/Docente/NotasDocente.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener notas");
        }
    }
}