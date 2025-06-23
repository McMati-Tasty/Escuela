package com.sample.core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sample.core.dao.CursoDao;
import com.sample.core.domain.Curso;
import com.sample.core.domain.Materia;

public class CursoServiceImpl implements CursoService {
    private final CursoDao cursoDao;

    public CursoServiceImpl(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }

    @Override
    public Map<Curso, List<Materia>> obtenerCursosYMateriasPorProfesor(int idProfesor) throws SQLException {
        return cursoDao.obtenerCursosYMateriasPorProfesor(idProfesor);
    }
    
    
    @Override
    public Map<Curso, List<Materia>> obtenerTodosLosCursosYMaterias(int idRector) throws SQLException {
        return cursoDao.obtenerTodosLosCursosYMaterias(idRector);
    }
    
}