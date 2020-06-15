package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Usuario;
import com.example.matricula.Views.Activities.CrearCuentaActivity;

public class CrearCuentaController {

    private CrearCuentaActivity crearCuentaActivity;

    public CrearCuentaController(CrearCuentaActivity crearCuentaActivity) {
        this.crearCuentaActivity = crearCuentaActivity;
    }

    public void postUsuario(Usuario usuario) throws Exception {
        Dao.getInstance(crearCuentaActivity).postUsuario(usuario);
    }
}
