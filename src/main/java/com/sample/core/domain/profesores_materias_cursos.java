package com.sample.core.domain;

public class profesores_materias_cursos {
    private int id;
    private int idProfesor;
    private int idRector;
    private int idMateria;
    private int idCurso;


    public profesores_materias_cursos(int id, int idProfesor, int idRector, int idMateria, int idCurso) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idRector = idRector;
        this.idMateria = idMateria;
        this.idCurso = idCurso;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    
    
    public int getIdRector() {
        return idRector;
    }

    public void setIdRector(int idRector) {
        this.idRector = idRector;
    }
    

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
