package com.example.sistemamatricula.Controllers;

import com.example.sistemamatricula.Models.EstudiantesModel;
import com.example.sistemamatricula.Views.Fragments.EstudiantesFragment;

import Logic.Estudiante;

public class EstudiantesController {

    private EstudiantesModel estudiantesModel;
    private EstudiantesFragment estudiantesFragment;

    public EstudiantesController(EstudiantesModel estudiantesModel, EstudiantesFragment estudiantesFragment) {
        this.estudiantesModel = estudiantesModel;
        this.estudiantesFragment = estudiantesFragment;
    }

    public EstudiantesModel getEstudiantesModel() {
        return estudiantesModel;
    }

    public void agregarEstudiante(int position, Estudiante estudiante) {
        this.estudiantesModel.addItem(position, estudiante);
    }

    public Estudiante eliminarEstudiante(int position) {
        return this.estudiantesModel.removeItem(position);
    }
}
