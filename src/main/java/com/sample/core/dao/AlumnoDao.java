package com.sample.core.dao;


import java.sql.SQLException;
import java.util.List;
import com.sample.core.domain.alumnos;

public interface AlumnoDao {
	 void eliminarAlumno(int idAlumno)throws SQLException;
}
