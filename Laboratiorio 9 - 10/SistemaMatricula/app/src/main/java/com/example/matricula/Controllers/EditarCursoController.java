package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Models.EditarCursoModel;
import com.example.matricula.Views.Activities.EditarCursoActivity;

public class EditarCursoController {

    private EditarCursoModel editarCursoModel;
    private EditarCursoActivity editarCursoActivity;

    public EditarCursoController(EditarCursoModel editarCursoModel, EditarCursoActivity editarCursoActivity) {
        this.editarCursoModel = editarCursoModel;
        this.editarCursoActivity = editarCursoActivity;
        this.editarCursoModel.addObserver(this.editarCursoActivity);
    }

    public void putCurso() {
        Dao.getInstance(editarCursoActivity).putCurso(editarCursoModel.getCurso());
        editarCursoModel.notificar("Curso actualizado");
    }
}