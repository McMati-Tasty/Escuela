package com.sample.core.domain;

public class alumnos_cursos {
    private int id;
    private int idAlumno;
    private int idCurso;

  
    public alumnos_cursos(int id, int idAlumno, int idCurso) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
