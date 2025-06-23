package com.sample.core.controller;

import com.sample.core.dao.NotasDao;
import com.sample.core.dao.NotasDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/guardar-nota")
public class GuardarNotaServlet extends HttpServlet {

    private NotasDao notasDao;

    @Override
    public void init() throws ServletException {
        notasDao = new NotasDaoImp(); // Usa la conexión interna
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAlumno = Integer.parseInt(request.getParameter("idNota")); // este es el id del alumno
            int nota1 = Integer.parseInt(request.getParameter("nota1"));
            int nota2 = Integer.parseInt(request.getParameter("nota2"));
            int nota3 = Integer.parseInt(request.getParameter("nota3"));

            // Recuperar los datos de la sesión
            HttpSession session = request.getSession();
            int idProfesor = (int) session.getAttribute("idProfesor");
            int idMateria = (int) session.getAttribute("idMateria");
            int idCurso    = (int) session.getAttribute("idCurso");

            notasDao.actualizarNotas(idAlumno, idProfesor, idMateria, idCurso, nota1, nota2, nota3);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar las notas");
        }
    }
}