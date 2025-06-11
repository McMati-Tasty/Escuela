package com.sample.core.domain;

import java.util.Objects;

public class Curso {
    private int id;
    private String nombre;

    public Curso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

 
    public int getId() { return id; }
    public String getNombre() { return nombre; }

 
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id == curso.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
