package com.example.sistemamatricula.Models;

import Logic.Usuario;

public class LoginModel {

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
}
