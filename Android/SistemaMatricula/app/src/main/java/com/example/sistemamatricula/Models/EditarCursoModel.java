package com.example.sistemamatricula.Models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Logic.Carrera;
import Logic.Curso;

public class EditarCursoModel extends Observable {

    private Curso curso;
    private ArrayList<Carrera> carreras;

    public EditarCursoModel(Curso curso) {
        this.curso = curso;
        this.carreras = new ArrayList<>();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void notificar(String arg) {
        setChanged();
        notifyObservers(arg);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}
