package com.sample.core.dao;

import com.sample.core.domain.notas;
import com.sample.core.dao.config.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotasDaoImp implements NotasDao {
	
	@Override
	public void eliminarNotasPorAlumno(int idAlumno) {
	    String sql = "UPDATE notas_materias SET nota1 = NULL, nota2 = NULL, nota3 = NULL WHERE id_alumno = ?";

	    try (Connection conn = Conexion.getInstance().dameConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idAlumno);
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void actualizarNotas(int idAlumno, int idProfesor, int idMateria, int idCurso,
	                            int nota1, int nota2, int nota3) {

	    String updateSql = "UPDATE notas_materias SET nota1 = ?, nota2 = ?, nota3 = ? " +
	                       "WHERE id_alumno = ? AND id_profesor = ? AND id_materia = ? AND id_curso = ?";

	    try (Connection connection = Conexion.getInstance().dameConnection();
	         PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

	        updateStmt.setInt(1, nota1);
	        updateStmt.setInt(2, nota2);
	        updateStmt.setInt(3, nota3);
	        updateStmt.setInt(4, idAlumno);
	        updateStmt.setInt(5, idProfesor);
	        updateStmt.setInt(6, idMateria);
	        updateStmt.setInt(7, idCurso);

	        int filasActualizadas = updateStmt.executeUpdate();

	        if (filasActualizadas == 0) {
	            String insertSql = "INSERT INTO notas_materias " +
	                    "(id_alumno, id_profesor, id_materia, id_curso, nota1, nota2, nota3) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

	            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
	                insertStmt.setInt(1, idAlumno);
	                insertStmt.setInt(2, idProfesor);
	                insertStmt.setInt(3, idMateria);
	                insertStmt.setInt(4, idCurso);
	                insertStmt.setInt(5, nota1);
	                insertStmt.setInt(6, nota2);
	                insertStmt.setInt(7, nota3);
	                insertStmt.executeUpdate();
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
    @Override
    public List<notas> obtenerNotasConPromedio(int idProfesor, int idMateria, int idCurso) throws SQLException {
        List<notas> lista = new ArrayList<>();

        String sql = "SELECT a.id, a.nombre, a.apellido, nm.nota1, nm.nota2, nm.nota3, " +
                "((nm.nota1 + nm.nota2 + nm.nota3)/3) AS promedio " +  
                "FROM notas_materias nm " +
                "JOIN alumnos a ON nm.id_alumno = a.id " +
                "WHERE nm.id_profesor = ? AND nm.id_materia = ? AND nm.id_curso = ?";

        try (Connection conn = Conexion.getInstance().dameConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfesor);
            stmt.setInt(2, idMateria);
            stmt.setInt(3, idCurso);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notas nota = new notas();
                    nota.setIdAlumno(rs.getInt("id"));
                    nota.setNombreAlumno(rs.getString("nombre"));
                    nota.setApellidoAlumno(rs.getString("apellido"));
                    nota.setNota1(rs.getInt("nota1"));
                    nota.setNota2(rs.getInt("nota2"));
                    nota.setNota3(rs.getInt("nota3"));
                    nota.setPromedio(rs.getInt("promedio"));
                    lista.add(nota);
                }
            }
        }

        return lista;
    }
}
