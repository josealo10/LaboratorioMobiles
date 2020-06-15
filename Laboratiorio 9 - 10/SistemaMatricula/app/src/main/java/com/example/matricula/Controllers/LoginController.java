package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Views.Activities.LoginActivity;

public class LoginController {

    private LoginActivity loginActivity;

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public String getUsuario(String usuario, String clave) throws Exception {
        return Dao.getInstance(loginActivity).getUsuario(usuario, clave);
    }

    public Estudiante getEstudiante(String usuario) {
        return Dao.getInstance(loginActivity).getEstudiante(usuario);
    }
}
