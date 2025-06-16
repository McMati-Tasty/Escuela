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

            int idProfesor = usuario.getId(); // ✔️ este sí está
            String idMateriaParam = request.getParameter("idMateria");
            String idCursoParam = request.getParameter("idCurso");

            if (idMateriaParam == null || idCursoParam == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros idMateria o idCurso");
                return;
            }

            int idMateria = Integer.parseInt(idMateriaParam);
            int idCurso = Integer.parseInt(idCursoParam);

            // ⬅️ SOLUCIÓN: Guardar en sesión
            session.setAttribute("idProfesor", idProfesor);
            session.setAttribute("idMateria", idMateria);
            session.setAttribute("idCurso", idCurso);

            List<notas> listaNotas = notasDao.obtenerNotasConPromedio(idProfesor, idMateria, idCurso);

            request.setAttribute("notas", listaNotas);
            request.getRequestDispatcher("/Notas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener notas");
        }
    }
}