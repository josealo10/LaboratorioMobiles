/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Logic.Alumno;
import Logic.Curso;
import Logic.Usuario;
import Services.Dao;
import java.util.ArrayList;

/**
 *
 * @author jaalf
 */
public class ModelAlumnos {
    private ArrayList<Alumno> alumnos;
    private Alumno alumno;
    private Usuario usuario;
    private ArrayList<Curso> cursosMatriculados;
    

    public ModelAlumnos() {
        alumnos = new ArrayList<>();
        alumno = new Alumno();
        cursosMatriculados = new ArrayList<>();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Curso> getCursosMatriculados() {
        return cursosMatriculados;
    }

    public void setCursosMatriculados(ArrayList<Curso> cursosMatriculados) {
        this.cursosMatriculados = cursosMatriculados;
    }  
}
