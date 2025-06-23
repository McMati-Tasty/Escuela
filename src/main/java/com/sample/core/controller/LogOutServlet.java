package com.sample.core.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Invalidar la sesión si existe
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Redirigir al login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}