package com.example.sistemamatricula.Models;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Logic.Carrera;
import Logic.Estudiante;

public class EditarEstudianteModel extends Observable {

    private Estudiante estudiante;
    private ArrayList<Carrera> carreras;

    public EditarEstudianteModel(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.carreras = new ArrayList<>();
    }

    public Estudiante getEstudiante() {
        return estudiante;
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