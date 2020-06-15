package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Curso;
import com.example.matricula.Models.EditarEstudianteModel;
import com.example.matricula.Views.Activities.EditarEstudianteActivity;

public class EditarEstudianteController {

    private EditarEstudianteModel editarEstudianteModel;
    private EditarEstudianteActivity editarEstudianteActivity;

    public EditarEstudianteController(EditarEstudianteModel editarEstudianteModel, EditarEstudianteActivity editarEstudianteActivity) {
        this.editarEstudianteModel = editarEstudianteModel;
        this.editarEstudianteActivity = editarEstudianteActivity;
    }

    public void putEstudiante() {
        Dao.getInstance(editarEstudianteActivity).putEstudiante(editarEstudianteModel.getEstudiante());
    }

    public void getCursosMatriculados() throws Exception {
        editarEstudianteModel.getEstudiante().setCursos(Dao.getInstance(editarEstudianteActivity).getCursosMatriculados(editarEstudianteModel.getEstudiante().getCedula()));
        editarEstudianteModel.notifyDataSetChanged();
    }

    public void getCursos() throws Exception {
        editarEstudianteModel.getCursos().addAll(Dao.getInstance(editarEstudianteActivity).getCursos());
    }

    public void postMatriculado(String estudiante, String curso) throws Exception {
        Dao.getInstance(editarEstudianteActivity).postMatriculado(estudiante, curso);
        getCursosMatriculados();
    }

    public void deleteMatriculado(String curso) {
        Dao.getInstance(editarEstudianteActivity).deleteMatriculado(editarEstudianteModel.getEstudiante().getCedula(), curso);
    }

    public void addItem(int position, Curso curso) {
        editarEstudianteModel.getEstudiante().getCursos().add(position, curso);
        editarEstudianteModel.notifyItemInserted(position);
    }

    public Curso deleteItem(int position) {
        Curso curso = editarEstudianteModel.getEstudiante().getCursos().get(position);

        editarEstudianteModel.getEstudiante().getCursos().remove(position);
        editarEstudianteModel.notifyItemRemoved(position);

        return curso;
    }
}
