package com.example.matricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matricula.Logic.Curso;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.R;
import com.example.matricula.databinding.ItemCursoBinding;

import java.util.ArrayList;

public class EditarEstudianteModel extends RecyclerView.Adapter<EditarEstudianteModel.CursoViewHolder> {

    private Estudiante estudiante;
    private ArrayList<Curso> cursos;

    public EditarEstudianteModel(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.cursos = new ArrayList<>();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso, parent, false);
        return new EditarEstudianteModel.CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        holder.showCurso(estudiante.getCursos().get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return estudiante.getCursos().size();
    }

    public class CursoViewHolder extends RecyclerView.ViewHolder {

        ItemCursoBinding itemCursoBinding;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);

            itemCursoBinding = ItemCursoBinding.bind(itemView);
        }

        public void showCurso(Curso curso) {
            itemCursoBinding.tvNombre.setText(curso.getNombre());
            itemCursoBinding.tvCodigo.setText(curso.getCodigo());
            itemCursoBinding.tvCreditos.setText("Créditos: " + curso.getCreditos());
        }
    }
}