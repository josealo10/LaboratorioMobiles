package com.example.sistemamatricula.Data;

import java.util.ArrayList;

import Logic.Carrera;
import Logic.Curso;
import Logic.Estudiante;
import Logic.Usuario;

public class Data {

    private static Data instance = null;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Carrera> carreras;
    private ArrayList<Curso> cursosInformatica;
    private ArrayList<Curso> cursosAdministracion;
    private ArrayList<Estudiante> estudiantes;

    private Data() {
        this.usuarios = new ArrayList<>();
        this.carreras = new ArrayList<>();
        this.cursosInformatica = new ArrayList<>();
        this.cursosAdministracion = new ArrayList<>();
        this.estudiantes = new ArrayList<>();

        this.usuarios.add(new Usuario("111", "111", "Estudiante"));
        this.usuarios.add(new Usuario("222", "222", "Estudiante"));
        this.usuarios.add(new Usuario("a", "333", "Administrador"));

        this.carreras.add(new Carrera(0, "Informática"));
        this.carreras.add(new Carrera(1, "Administración"));

        this.cursosInformatica.add(new Curso(0, 4, 4, "Fundamentos de informática", this.carreras.get(0)));
        this.cursosInformatica.add(new Curso(1, 2, 2, "Programación IV", this.carreras.get(0)));
        this.cursosInformatica.add(new Curso(2, 3, 2, "Ingeniería III", this.carreras.get(0)));
        this.cursosInformatica.add(new Curso(3, 5, 3, "Programación II", this.carreras.get(0)));

        this.cursosAdministracion.add(new Curso(4, 1, 5, "Fundamentos de administración", this.carreras.get(1)));
        this.cursosAdministracion.add(new Curso(5, 4, 3, "Finanzas", this.carreras.get(1)));
        this.cursosAdministracion.add(new Curso(6, 3, 5, "Contabilidad", this.carreras.get(1)));
        this.cursosAdministracion.add(new Curso(7, 5, 4, "Liderazgo", this.carreras.get(1)));

        this.estudiantes.add(new Estudiante("117280709", "Alessandro Fazio Pérez", "87282651", "alessandro.fazio.perez@gmail.com", this.carreras.get(0), this.cursosInformatica, this.usuarios.get(0)));
        this.estudiantes.add(new Estudiante("402420953", "Alonso Alfaro Pérez", "88345509", "josealonso.alfa@gmail.com", this.carreras.get(1), this.cursosAdministracion, this.usuarios.get(1)));
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }

        return instance;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public ArrayList<Curso> getCursosInformatica() {
        return cursosInformatica;
    }

    public ArrayList<Curso> getCursosAdministracion() {
        return cursosAdministracion;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}