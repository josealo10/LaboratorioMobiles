package com.example.sistemamatricula.Models;

import java.util.Observable;
import java.util.Observer;

import Logic.Usuario;

public class LoginModel extends Observable {

    public static String URL_SERVIDOR;
    private Usuario usuario;

    public LoginModel() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
