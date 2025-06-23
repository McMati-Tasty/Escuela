package com.sample.core.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sample.core.domain.Curso;
import com.sample.core.domain.Materia;

public interface CursoDao {
    Map<Curso, List<Materia>> obtenerCursosYMateriasPorProfesor(int idProfesor) throws SQLException;
    Map<Curso, List<Materia>> obtenerTodosLosCursosYMaterias(int idRector) throws SQLException;
    
    
}
