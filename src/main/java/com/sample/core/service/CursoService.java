package com.sample.core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sample.core.domain.Curso;
import com.sample.core.domain.Materia;

public interface CursoService {
    Map<Curso, List<Materia>> obtenerCursosYMateriasPorProfesor(int idProfesor) throws SQLException;
}