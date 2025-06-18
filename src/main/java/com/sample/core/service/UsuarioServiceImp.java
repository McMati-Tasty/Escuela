package com.sample.core.service;

import com.sample.core.exceptions.UsuarioNoExisteException;
import com.sample.core.dao.LoginDao;
import com.sample.core.dao.LoginDaoImp;


public class UsuarioServiceImp implements UsuarioService{

	private LoginDao loginDao= new LoginDaoImp();
	
	
	@Override
	public void validarNombre(String correo , String contraseñaHash)throws Exception {
		try {
			loginDao.usuarioExiste(correo);
			
			loginDao.passwordExiste(correo,contraseñaHash);
	
			
		} catch (Exception e1) {
			throw new UsuarioNoExisteException("No existe el usuario o la pass no es valida",e1);
		}
	}

}