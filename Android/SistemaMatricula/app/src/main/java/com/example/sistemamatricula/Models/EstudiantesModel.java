package com.example.sistemamatricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.R;

import java.util.ArrayList;

import Logic.Estudiante;

public class EstudiantesModel extends RecyclerView.Adapter<EstudiantesModel.EstudianteViewHolder> {

    private ArrayList<Estudiante> estudiantes;

    public EstudiantesModel() {
        this.estudiantes = new ArrayList<>();
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, null, false);
        return new EstudianteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudiantesModel.EstudianteViewHolder holder, int position) {
        holder.mostrarEstudiante(estudiantes.get(position));
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre, tv_cedula, tv_carrera;

        public EstudianteViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_cedula = itemView.findViewById(R.id.tv_cedula);
            tv_carrera = itemView.findViewById(R.id.tv_carrera);
        }

        public void mostrarEstudiante(Estudiante estudiante) {
            tv_nombre.setText(estudiante.getNombre());
            tv_cedula.setText(estudiante.getCedula());
            tv_carrera.setText(estudiante.getCarrera().getNombre());
        }
    }
}
