package com.sample.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.core.dao.LoginDao;
import com.sample.core.dao.LoginDaoImp;
import com.sample.core.domain.Usuarios;
import com.sample.core.exceptions.ClaveNoExisteException;
import com.sample.core.exceptions.UsuarioNoExisteException;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        // Ahora comprobamos el objeto entero
        Usuarios u = (Usuarios) session.getAttribute("usuarioLogueado");
        if (u != null) {
            resp.sendRedirect(req.getContextPath() + "/cursos");  
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("txtUsuario");
        String clave  = req.getParameter("txtClave");

        LoginDao loginDao = new LoginDaoImp();
        HttpSession session = req.getSession(true);

        try {
            //Validar existencia y contraseña
            loginDao.usuarioExiste(correo);
            loginDao.passwordExiste(correo, clave);

            //Traer datos completos del usuario
            Usuarios usuarioObj = loginDao.obtenerUsuarioPorCorreo(correo);

            // Guardar el objeto Usuarios bajo "usuarioLogueado"
            session.setAttribute("usuarioLogueado", usuarioObj);

            // Opcional: seguir guardando nombre/apellido por separado
            session.setAttribute("nombreUsuario", usuarioObj.getNombre());
            session.setAttribute("apellidoUsuario", usuarioObj.getApellido());

            // redirect a /cursos para que lo atienda CursosControllerServlet
            resp.sendRedirect(req.getContextPath() + "/cursos");

        } catch (UsuarioNoExisteException | ClaveNoExisteException e) {
            req.setAttribute("error", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
        }
    }
}

