package com.example.matricula.Controllers;

import com.example.matricula.Database.Dao;
import com.example.matricula.Logic.Curso;
import com.example.matricula.Models.CursosModel;
import com.example.matricula.Views.Fragments.CursosFragment;

public class CursosController {

    private CursosModel cursosModel;
    private CursosFragment cursosFragment;

    public CursosController(CursosModel cursosModel, CursosFragment cursosFragment) {
        this.cursosModel = cursosModel;
        this.cursosFragment = cursosFragment;
    }

    public CursosModel getCursosModel() {
        return cursosModel;
    }

    public void getCursos() throws Exception {
        cursosModel.getCursos().clear();
        cursosModel.getCursosCopia().clear();

        cursosModel.getCursos().addAll(Dao.getInstance(cursosFragment.getContext()).getCursos());
        cursosModel.getCursosCopia().addAll(cursosModel.getCursos());

        cursosModel.notifyDataSetChanged();
    }

    public void deleteCurso(String codigo) {
        Dao.getInstance(cursosFragment.getContext()).deleteCurso(codigo);
    }

    public void addItem(int position, Curso curso) {
        cursosModel.getCursos().add(position, curso);
        cursosModel.notifyItemInserted(position);
    }

    public Curso deleteItem(int position) {
        Curso curso = cursosModel.getCursos().get(position);

        cursosModel.getCursos().remove(position);
        cursosModel.notifyItemRemoved(position);

        return curso;
    }
}