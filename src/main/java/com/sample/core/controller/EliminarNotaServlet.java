package com.sample.core.controller;

import com.sample.core.dao.NotasDao;
import com.sample.core.dao.NotasDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EliminarNotaServlet")
public class EliminarNotaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NotasDao notasDao;

    @Override
    public void init() throws ServletException {
        notasDao = new NotasDaoImp();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAlumnoStr = request.getParameter("idAlumno");

        try {
            int idAlumno = Integer.parseInt(idAlumnoStr);
            
            // Llama al método DAO que borra las notas
            notasDao.eliminarNotasPorAlumno(idAlumno);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}