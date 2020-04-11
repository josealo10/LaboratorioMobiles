package com.example.sistemamatricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.Data.Data;
import com.example.sistemamatricula.R;

import java.util.ArrayList;

import Logic.Estudiante;

public class EstudiantesModel extends RecyclerView.Adapter<EstudiantesModel.EstudianteViewHolder> {

    private ArrayList<Estudiante> estudiantes;
    private OnEstudianteClickListener onEstudianteClickListener;

    public EstudiantesModel(OnEstudianteClickListener onEstudianteClickListener) {
        this.estudiantes = new ArrayList<>(Data.getInstance().getEstudiantes());
        this.onEstudianteClickListener = onEstudianteClickListener;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, null, false);
        return new EstudianteViewHolder(view, onEstudianteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudiantesModel.EstudianteViewHolder holder, int position) {
        holder.mostrarEstudiante(estudiantes.get(position));
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    public void addItem(int position, Estudiante estudiante) {
        this.estudiantes.add(position, estudiante);
        this.notifyItemInserted(position);
    }

    public Estudiante removeItem(int position) {
        Estudiante estudiante = this.estudiantes.get(position);
        this.estudiantes.remove(position);
        this.notifyItemRemoved(position);

        return estudiante;
    }

    public interface OnEstudianteClickListener {
        void onEstudianteClick(Estudiante estudiante);
    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_nombre, tv_cedula, tv_carrera;
        OnEstudianteClickListener onEstudianteClickListener;

        public EstudianteViewHolder(@NonNull View itemView, OnEstudianteClickListener onEstudianteClickListener) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_cedula = itemView.findViewById(R.id.tv_cedula);
            tv_carrera = itemView.findViewById(R.id.tv_carrera);

            this.onEstudianteClickListener = onEstudianteClickListener;

            itemView.setOnClickListener(this);
        }

        public void mostrarEstudiante(Estudiante estudiante) {
            tv_nombre.setText(estudiante.getNombre());
            tv_cedula.setText(estudiante.getCedula());
            tv_carrera.setText(estudiante.getCarrera().getNombre());
        }

        @Override
        public void onClick(View v) {
            onEstudianteClickListener.onEstudianteClick(estudiantes.get(getAdapterPosition()));
        }
    }
}
