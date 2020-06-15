package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Models.CrearEstudianteModel;
import com.example.matricula.Views.Activities.CrearEstudianteActivity;

public class CrearEstudianteController {

    private CrearEstudianteModel crearEstudianteModel;
    private CrearEstudianteActivity crearEstudianteActivity;

    public CrearEstudianteController(CrearEstudianteModel crearEstudianteModel, CrearEstudianteActivity crearEstudianteActivity) {
        this.crearEstudianteModel = crearEstudianteModel;
        this.crearEstudianteActivity = crearEstudianteActivity;
        this.crearEstudianteModel.addObserver(this.crearEstudianteActivity);
    }

    public void postEstudiante(Estudiante estudiante) throws Exception {
        Dao.getInstance(crearEstudianteActivity).postUsuario(estudiante.getUsuario());
        Dao.getInstance(crearEstudianteActivity).postEstudiante(estudiante);
        crearEstudianteModel.notificar("Estudiante creado");
    }
}
