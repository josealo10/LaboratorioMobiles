package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Models.EstudiantesModel;
import com.example.matricula.Views.Fragments.EstudiantesFragment;

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

    public void getEstudiantes() throws Exception {
        estudiantesModel.getEstudiantes().clear();
        estudiantesModel.getEstudiantesCopia().clear();

        estudiantesModel.getEstudiantes().addAll(Dao.getInstance(estudiantesFragment.getContext()).getEstudiantes());
        estudiantesModel.getEstudiantesCopia().addAll(estudiantesModel.getEstudiantes());

        estudiantesModel.notifyDataSetChanged();
    }

    public void deleteEstudiante(String cedula, String usuario) {
        Dao.getInstance(estudiantesFragment.getContext()).deleteEstudiante(cedula, usuario);
    }

    public void addItem(int position, Estudiante estudiante) {
        estudiantesModel.getEstudiantes().add(position, estudiante);
        estudiantesModel.notifyItemInserted(position);
    }

    public Estudiante deleteItem(int position) {
        Estudiante estudiante = estudiantesModel.getEstudiantes().get(position);

        estudiantesModel.getEstudiantes().remove(position);
        estudiantesModel.notifyItemRemoved(position);

        return estudiante;
    }
}
