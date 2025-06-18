package com.sample.core.dao;

import java.sql.SQLException;
import java.util.List;
import com.sample.core.domain.notas;

public interface NotasDao {
	void actualizarNotas(int idAlumno, int idProfesor, int idMateria, int idCurso, int nota1, int nota2, int nota3) throws SQLException;
    List<notas> obtenerNotasConPromedio(int idProfesor, int idMateria, int idCurso) throws Exception;
    List<notas> obtenerNotasParaRector( int idMateria, int idCurso) throws Exception;
    void eliminarNotasPorAlumno(int idAlumno);
}



