package com.sample.core.exceptions;

public class UsuarioNoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNoExisteException(String mensaje) {
		super(mensaje);
	}
	
	public UsuarioNoExisteException(String mensaje, Throwable e) {
		super(mensaje, e);
	}
}