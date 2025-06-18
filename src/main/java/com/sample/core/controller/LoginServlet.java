package com.sample.core.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
       
        HttpSession session = req.getSession(false);

        if (session != null) {
            Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

            if (usuario != null) {
                redirigirSegunRol(usuario, req, resp);
                return;
            }
        }

        RequestDispatcher rd = req.getRequestDispatcher("/Login/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("txtUsuario");
        String clave = req.getParameter("txtClave");
        
        // Chequear si es una petición AJAX (lo agregué después de investigar un poco)
        boolean esAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        
        LoginDao loginDao = new LoginDaoImp();
        HttpSession session = req.getSession(true);

        try {
            
            loginDao.usuarioExiste(correo);
            loginDao.passwordExiste(correo, clave);
            Usuarios usuarioObj = loginDao.obtenerUsuarioPorCorreo(correo);

            session.setAttribute("usuarioLogueado", usuarioObj);
            session.setAttribute("rol", usuarioObj.getRol());
            session.setAttribute("nombreUsuario", usuarioObj.getNombre());
            session.setAttribute("apellidoUsuario", usuarioObj.getApellido());

            // Si es AJAX, respondemos con JSON en lugar de redirigir
            if(esAjax) {
                // Esto lo tuve que buscar para responder en formato JSON
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                
                // Armamos el JSON manualmente como string
                String jsonResponse = "{"
                    + "\"exito\": true,"
                    + "\"mensaje\": \"¡Bienvenido " + usuarioObj.getNombre() + "!\","
                    + "\"redireccion\": \"" + obtenerUrlRedireccion(usuarioObj.getRol(), req) + "\""
                    + "}";
                
                out.print(jsonResponse);
                return;  // Importante salir del método aquí
            } 
            // Si no es AJAX, seguimos con el flujo normal
            else {
                redirigirSegunRol(usuarioObj, req, resp);
            }

        } catch (UsuarioNoExisteException | ClaveNoExisteException e) {
            // Manejo diferente para AJAX
            if(esAjax) {
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.print("{"
                    + "\"exito\": false,"
                    + "\"error\": \"" + e.getMessage() + "\""
                    + "}");
            } 
            // Flujo tradicional para no-AJAX
            else {
                req.setAttribute("error", e.getMessage());
                RequestDispatcher rd = req.getRequestDispatcher("/Login/login.jsp");
                rd.forward(req, resp);
            }
        }
    }

    // Método para obtener la URL de redirección (lo separé para reutilizar código)
    private String obtenerUrlRedireccion(String rol, HttpServletRequest req) {
        String contexto = req.getContextPath();
        if ("profesor".equalsIgnoreCase(rol)) {
            return contexto + "/cursos?rol=profesor";
        } else if ("rector".equalsIgnoreCase(rol)) {
            return contexto + "/cursos?rol=rector";
        } else {
            return contexto + "/Login/login.jsp?error=Rol+no+válido";
        }
    }
    
    // Tu método original con pequeño cambio para reutilizar
    private void redirigirSegunRol(Usuarios usuario, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(obtenerUrlRedireccion(usuario.getRol(), req));
    }
}