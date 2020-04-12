package com.example.sistemamatricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ItemCursoBinding;

import java.util.ArrayList;

import Logic.Curso;

public class CursosModel extends RecyclerView.Adapter<CursosModel.CursoViewHolder> {

    private ArrayList<Curso> cursos;

    public CursosModel() {
        this.cursos = new ArrayList<>();
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    @NonNull
    @Override
    public CursosModel.CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso, null, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursosModel.CursoViewHolder holder, int position) {
        holder.mostrarCurso(cursos.get(position));
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public class CursoViewHolder extends RecyclerView.ViewHolder {
        ItemCursoBinding binding;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCursoBinding.bind(itemView);
        }

        public void mostrarCurso(Curso curso) {
            binding.tvNombre.setText(curso.getNombre());
            binding.tvCarrera.setText(curso.getCarrera().getNombre());
        }
    }
}
