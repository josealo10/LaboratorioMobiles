package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Curso;
import com.example.matricula.Models.CrearCursoModel;
import com.example.matricula.Views.Activities.CrearCursoActivity;

public class CrearCursoController {

    private CrearCursoModel crearCursoModel;
    private CrearCursoActivity crearCursoActivity;

    public CrearCursoController(CrearCursoModel crearCursoModel, CrearCursoActivity crearCursoActivity) {
        this.crearCursoModel = crearCursoModel;
        this.crearCursoActivity = crearCursoActivity;
        this.crearCursoModel.addObserver(this.crearCursoActivity);
    }

    public void postCurso(Curso curso) throws Exception {
        Dao.getInstance(crearCursoActivity).postCurso(curso);
        crearCursoModel.notificar("Curso creado");
    }
}
