package com.example.sistemamatricula.Logic;

import java.util.ArrayList;

public class Carrera {
    
    private int codigo;
    private String nombre;
    private ArrayList<Curso> cursos;

    public Carrera(int codigo, String nombre, ArrayList<Curso> cursos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cursos = cursos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}
