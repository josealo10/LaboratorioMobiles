package com.example.sistemamatricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ItemEstudianteBinding;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import Logic.Curso;
import Logic.Estudiante;

public class EstudiantesModel extends RecyclerView.Adapter<EstudiantesModel.EstudianteViewHolder> implements Filterable {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Estudiante> estudiantesCopia;
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Estudiante> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(estudiantesCopia);

            } else {
                String filterPattern = Normalizer.normalize(constraint, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();

                for (Estudiante estudiante : estudiantesCopia) {
                    if (Normalizer.normalize(estudiante.getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)
                            || Normalizer.normalize(estudiante.getCarrera().getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)
                            || Normalizer.normalize(estudiante.getCedula() + "", Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)) {
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

    public EstudiantesModel() {
        this.estudiantes = new ArrayList<>();
        this.estudiantesCopia = new ArrayList<>();
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder {
        ItemEstudianteBinding binding;

        public EstudianteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemEstudianteBinding.bind(itemView);
        }

        public void mostrarEstudiante(Estudiante estudiante) {
            binding.tvNombre.setText(estudiante.getNombre());
            binding.tvCedula.setText(estudiante.getCedula());
            binding.tvCarrera.setText(estudiante.getCarrera().getNombre());
        }
    }
}
