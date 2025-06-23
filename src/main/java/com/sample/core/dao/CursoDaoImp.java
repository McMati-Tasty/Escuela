package com.sample.core.dao;

import java.sql.*;
import java.util.*;
import com.sample.core.domain.Curso;
import com.sample.core.domain.Materia;

public  class CursoDaoImp implements CursoDao {
    private Connection connection;

    public CursoDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<Curso, List<Materia>> obtenerCursosYMateriasPorProfesor(int idProfesor) throws SQLException {
        Map<Curso, List<Materia>> cursosConMaterias = new LinkedHashMap<>();
        Set<Integer> cursosProcesados = new HashSet<>(); // Para evitar duplicados
        
        String sql = "SELECT c.id, c.nombre, m.id AS materia_id, m.nombre AS materia_nombre " +
                     "FROM profesores_materias_cursos pmc " +
                     "JOIN cursos c ON pmc.id_curso = c.id " +
                     "JOIN materias m ON pmc.id_materia = m.id " +
                     "WHERE pmc.id_profesor = ? " +
                     "ORDER BY c.id, m.nombre"; 

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProfesor);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int cursoId = rs.getInt("id");
                
                // Si ya procesamos este curso, continuar
                if (cursosProcesados.contains(cursoId)) continue;
                
                cursosProcesados.add(cursoId);
                Curso curso = new Curso(cursoId, rs.getString("nombre"));
                List<Materia> materias = new ArrayList<>();
                
                // Agregar todas las materias de este curso
                materias.add(new Materia(
                    rs.getInt("materia_id"),
                    rs.getString("materia_nombre")
                ));
                
                // Buscar más materias para el mismo curso
                while (rs.next() && rs.getInt("id") == cursoId) {
                    materias.add(new Materia(
                        rs.getInt("materia_id"),
                        rs.getString("materia_nombre")
                    ));
                }
                
                // Retroceder un paso si salimos del bucle interno
                if (!rs.isAfterLast()) {
                    rs.previous();
                }
                
                cursosConMaterias.put(curso, materias);
            }
        }
        return cursosConMaterias;
    }
    




    @Override
    public Map<Curso, List<Materia>> obtenerTodosLosCursosYMaterias(int idRector) throws SQLException {
        Map<Curso, List<Materia>> cursosConMaterias = new LinkedHashMap<>();

        String sql = "SELECT c.id AS curso_id, c.nombre AS curso_nombre, m.id AS materia_id, m.nombre AS materia_nombre " +
                     "FROM cursos c " +
                     "LEFT JOIN profesores_materias_cursos pmc ON c.id = pmc.id_curso " +
                     "LEFT JOIN materias m ON pmc.id_materia = m.id " +
                     "ORDER BY c.id, m.nombre";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
        	ResultSet rs = ps.executeQuery();

            Curso cursoActual = null;
            List<Materia> materias = null;
            int ultimoCursoId = -1;

            while (rs.next()) {
                int cursoId = rs.getInt("curso_id");

                if (cursoActual == null || cursoId != ultimoCursoId) {
                    cursoActual = new Curso(cursoId, rs.getString("curso_nombre"));
                    materias = new ArrayList<>();
                    cursosConMaterias.put(cursoActual, materias);
                    ultimoCursoId = cursoId;
                }

                int materiaId = rs.getInt("materia_id");
                String materiaNombre = rs.getString("materia_nombre");

                if (materiaNombre != null) { // Para evitar materias nulas por LEFT JOIN
                    Materia materia = new Materia(materiaId, materiaNombre);
                    materias.add(materia);
                }
            }
        }

        return cursosConMaterias;
    }




}