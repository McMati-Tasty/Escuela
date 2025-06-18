package com.sample.core.exceptions;

public class ClaveNoExisteException extends Exception {


	private static final long serialVersionUID = 1L;
	public ClaveNoExisteException(String mensaje) {
		super(mensaje);
	}
	public ClaveNoExisteException(String mensaje, Throwable e) {
		super(mensaje, e);
	}
}
