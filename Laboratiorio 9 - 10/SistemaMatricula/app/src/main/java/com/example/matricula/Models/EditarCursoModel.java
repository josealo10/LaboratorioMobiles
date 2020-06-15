package com.example.matricula.Models;

import com.example.matricula.Logic.Curso;

import java.util.Observable;
import java.util.Observer;

public class EditarCursoModel extends Observable {

    private Curso curso;

    public EditarCursoModel(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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