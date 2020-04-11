package com.example.sistemamatricula.Controllers;

import com.example.sistemamatricula.Data.Data;
import com.example.sistemamatricula.Models.CrearEstudianteModel;
import com.example.sistemamatricula.Views.Activities.CrearEstudianteActivity;

import Logic.Carrera;
import Logic.Estudiante;

public class CrearEstudianteController {

    private CrearEstudianteModel crearEstudianteModel;

    public CrearEstudianteController(CrearEstudianteModel crearEstudianteModel, CrearEstudianteActivity crearEstudianteActivity) {
        this.crearEstudianteModel = crearEstudianteModel;
        this.crearEstudianteModel.addObserver(crearEstudianteActivity);
    }

    public void getCarrerasRequest() {
        crearEstudianteModel.getCarreras().addAll(Data.getInstance().getCarreras());
        crearEstudianteModel.notificar("Carreras obtenidas");
    }

    public void postEstudianteRequest(Estudiante estudiante) {
        Data.getInstance().getEstudiantes().add(estudiante);

        crearEstudianteModel.setEstudiante(estudiante);
        crearEstudianteModel.notificar("Estudiante creado");
    }
}
