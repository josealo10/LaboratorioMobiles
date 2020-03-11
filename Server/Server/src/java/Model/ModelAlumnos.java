/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Logic.Alumno;
import Services.Dao;
import java.util.ArrayList;

/**
 *
 * @author jaalf
 */
public class ModelAlumnos {
    private ArrayList<Alumno> alumnos;
    private Alumno alumno;
    private Dao db;

    public ModelAlumnos() {
        alumnos = new ArrayList<>();
        alumno = new Alumno();
        db = new Dao();
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

    public Dao getDb() {
        return db;
    }

    public void setDb(Dao db) {
        this.db = db;
    }    
}
