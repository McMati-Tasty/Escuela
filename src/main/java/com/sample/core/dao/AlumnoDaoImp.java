package com.sample.core.dao;

import com.sample.core.dao.config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlumnoDaoImp implements AlumnoDao {

    @Override
    public void eliminarAlumno(int idAlumno) {
        String sqlEliminarNotas = "DELETE FROM notas_materias WHERE id_alumno = ?";
        String sqlEliminarAlumno = "DELETE FROM alumnos WHERE id = ?";

        try (Connection conn = Conexion.getInstance().dameConnection()) {

            // Primero eliminar las notas del alumno
            try (PreparedStatement stmtNotas = conn.prepareStatement(sqlEliminarNotas)) {
                stmtNotas.setInt(1, idAlumno);
                stmtNotas.executeUpdate();
            }

            // Luego eliminar el alumno
            try (PreparedStatement stmtAlumno = conn.prepareStatement(sqlEliminarAlumno)) {
                stmtAlumno.setInt(1, idAlumno);
                stmtAlumno.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}