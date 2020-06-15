package com.example.matricula.Models;

import com.example.matricula.Logic.Estudiante;

import java.util.Observable;
import java.util.Observer;

public class CrearEstudianteModel extends Observable {

    private Estudiante estudiante;

    public CrearEstudianteModel() {
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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
