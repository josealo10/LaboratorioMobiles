package com.example.sistemamatricula.Controllers;

import com.example.sistemamatricula.Data.Data;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Views.Activities.LoginActivity;

import Logic.Usuario;

public class LoginController {

    private LoginModel loginModel;
    private LoginActivity loginActivity;

    public LoginController(LoginModel loginModel, LoginActivity loginActivity) {
        this.loginModel = loginModel;
        this.loginActivity = loginActivity;
    }

    public String login(String usuario, String clave) throws Exception {
        for (Usuario u : Data.getInstance().getUsuarios()) {
            if (u.getUsuario().equals(usuario) && u.getClave().equals(clave)) {
                loginModel.getUsuario().setUsuario(usuario);
                loginModel.getUsuario().setClave(clave);
                loginModel.getUsuario().setRol(u.getRol());

                return u.getRol();
            }
        }

        throw new Exception("Usuario no existe");
    }
}
