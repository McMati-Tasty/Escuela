package com.sample.core.dao;

import com.sample.core.domain.Usuarios;
import com.sample.core.exceptions.ClaveNoExisteException;
import com.sample.core.exceptions.UsuarioNoExisteException;

public interface LoginDao {
    
   
    public void usuarioExiste(String correo) throws UsuarioNoExisteException; 

   
    public void passwordExiste(String correo, String contraseñaHash) throws ClaveNoExisteException;

	Usuarios obtenerUsuarioPorCorreo(String correo);
}
