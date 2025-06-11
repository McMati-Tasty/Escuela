package com.sample.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

@WebServlet(urlPatterns = { "/identificar"})
public class VerificarLoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	UsuarioService usuarioService = new UsuarioServiceImp();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String nombre = (String) req.getParameter("txtUsuario");
			String clave = (String) req.getParameter("txtClave");
			try {
				usuarioService.validarNombre(nombre, clave);
				HttpSession misession = req.getSession(true);
				misession.setAttribute("usuario","usuario");
				
				resp.sendRedirect("/login");
			} catch (Exception e) {
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginIncorrecto.jsp");
				dispatcher.forward(req, resp);e.getMessage();
		}	
	}
}
