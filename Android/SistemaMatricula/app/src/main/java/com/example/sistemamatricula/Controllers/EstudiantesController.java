package com.example.sistemamatricula.Controllers;

import com.example.sistemamatricula.Data.Data;
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

    public void getEstudiantesRequest() {
        estudiantesModel.getEstudiantes().clear();
        estudiantesModel.getEstudiantes().addAll(Data.getInstance().getEstudiantes());
        estudiantesModel.notifyDataSetChanged();
    }

    public void addEstudianteRequest(int position, Estudiante estudiante) {
        Data.getInstance().getEstudiantes().add(position, estudiante);

        estudiantesModel.getEstudiantes().add(position, estudiante);
        estudiantesModel.notifyItemInserted(position);
    }

    public Estudiante deleteEstudianteRequest(int position) {
        Data.getInstance().getEstudiantes().remove(position);

        Estudiante estudiante = estudiantesModel.getEstudiantes().get(position);
        estudiantesModel.getEstudiantes().remove(position);
        estudiantesModel.notifyItemRemoved(position);

        return estudiante;
    }
}
