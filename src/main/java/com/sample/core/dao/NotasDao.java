package com.sample.core.dao;

import java.sql.SQLException;
import java.util.List;
import com.sample.core.domain.notas;

public interface NotasDao {
    List<notas> obtenerNotasConPromedio(int idProfesor, int idMateria, int idCurso) throws Exception;
}



