package com.sample.core.controller;

import com.sample.core.dao.AlumnoDao;
import com.sample.core.dao.AlumnoDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;



@WebServlet("/EliminarAlumno")
public class EliminarAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int idAlumno = Integer.parseInt(request.getParameter("id"));

        AlumnoDao alumnoDao = new AlumnoDaoImp();
        try {
            alumnoDao.eliminarAlumno(idAlumno);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"No se pudo eliminar.\"}");
        }
    }
}
