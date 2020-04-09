package com.example.sistemamatricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.Logic.Estudiante;
import com.example.sistemamatricula.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class EstudianteModel extends RecyclerView.Adapter<EstudianteModel.EstudianteViewHolder> implements Filterable {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Estudiante> estudiantesCopia;
    private OnEstudianteClickListener onEstudianteClickListener;
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Estudiante> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(estudiantesCopia);

            } else {
                String filterPattern = Normalizer.normalize(constraint, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();

                for (Estudiante estudiante : estudiantesCopia) {
                    if (Normalizer.normalize(estudiante.getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)) {
                        filteredList.add(estudiante);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            estudiantes.clear();
            estudiantes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public EstudianteModel(OnEstudianteClickListener onEstudianteClickListener) {
        this.estudiantes = new ArrayList<>();
        this.estudiantesCopia = new ArrayList<>();
        this.onEstudianteClickListener = onEstudianteClickListener;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public ArrayList<Estudiante> getEstudiantesCopia() {
        return estudiantesCopia;
    }

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, null, false);
        return new EstudianteViewHolder(view, onEstudianteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteModel.EstudianteViewHolder holder, int position) {
        holder.mostrarEstudiante(estudiantes.get(position));
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public interface OnEstudianteClickListener {
        void onEstudianteClick(Estudiante estudiante);
    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_product_title, tv_product_description, tv_price, tv_unitMeasurement;
        OnEstudianteClickListener onEstudianteClickListener;

        public EstudianteViewHolder(@NonNull View itemView, OnEstudianteClickListener onEstudianteClickListener) {
            super(itemView);

            this.onEstudianteClickListener = onEstudianteClickListener;

            itemView.setOnClickListener(this);
        }

        public void mostrarEstudiante(Estudiante estudiante) {

        }

        @Override
        public void onClick(View v) {
            onEstudianteClickListener.onEstudianteClick(estudiantes.get(getAdapterPosition()));
        }
    }
}
