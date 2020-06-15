package com.example.matricula.Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Estudiante implements Serializable {

    private String cedula, nombre, apellidos;
    private int edad;
    private Usuario usuario;
    private ArrayList<Curso> cursos;

    public Estudiante(String cedula, String nombre, String apellidos, int edad, Usuario usuario, ArrayList<Curso> cursos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.usuario = usuario;
        this.cursos = cursos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}