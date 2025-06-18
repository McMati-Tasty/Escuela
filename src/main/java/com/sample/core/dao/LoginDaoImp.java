package com.sample.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sample.core.exceptions.ClaveNoExisteException;
import com.sample.core.exceptions.UsuarioNoExisteException;
import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.Usuarios;

public class LoginDaoImp implements LoginDao {

    private Conexion conexion = Conexion.getInstance();
    
    
    // Validar existencia de usuario

    private static final String QUERY_NOMBRE  = "SELECT * FROM usuarios WHERE correo = ?";
    @Override
    public void usuarioExiste(String correo) throws UsuarioNoExisteException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conexion.dameConnection().prepareStatement(QUERY_NOMBRE);
            st.setString(1, correo);
            rs = st.executeQuery();
            if (!rs.first()) {
                throw new UsuarioNoExisteException("El usuario con ese correo no existe");
            }
        } catch (Exception e1) {
            throw new UsuarioNoExisteException("Error al verificar la existencia del usuario", e1);
        } finally {
            try { if (rs != null) rs.close(); if (st != null) st.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

   
    //  Validar contraseña correcta
   
    private static final String QUERY_CLAVE   = "SELECT * FROM usuarios WHERE correo = ? AND contraseña_hash = ?";
    @Override
    public void passwordExiste(String correo, String clave) throws ClaveNoExisteException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conexion.dameConnection().prepareStatement(QUERY_CLAVE);
            st.setString(1, correo);
            st.setString(2, clave);
            rs = st.executeQuery();
            if (!rs.first()) {
                throw new ClaveNoExisteException("La contraseña es incorrecta para ese correo");
            }
        } catch (Exception e) {
            throw new ClaveNoExisteException("Error al verificar la contraseña", e);
        } finally {
            try { if (rs != null) rs.close(); if (st != null) st.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }


    // Recuperar nombre y apellido
 
    @Override
    public Usuarios obtenerUsuarioPorCorreo(String correo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Usuarios usuario = null;

        try {
            st = conexion.dameConnection()
                       .prepareStatement("SELECT id, nombre, apellido, correo, contraseña_hash, rol FROM usuarios WHERE correo = ?");
            st.setString(1, correo);
            rs = st.executeQuery();

            if (rs.next()) {
                // Leemos todos los campos 
                int    id    = rs.getInt("id");
                String nom   = rs.getString("nombre");
                String ape   = rs.getString("apellido");
                String mail  = rs.getString("correo");
                String hash  = rs.getString("contraseña_hash");
                String rol   = rs.getString("rol");
                usuario = new Usuarios(id, nom, ape, mail, hash, rol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); if (st != null) st.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }

        return usuario;
    }
}

