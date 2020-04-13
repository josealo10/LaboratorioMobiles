package com.example.sistemamatricula.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ItemCursoBinding;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import Logic.Curso;

public class CursosModel extends RecyclerView.Adapter<CursosModel.CursoViewHolder> implements Filterable {

    private ArrayList<Curso> cursos;
    private ArrayList<Curso> cursosCopia;
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Curso> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(cursosCopia);

            } else {
                String filterPattern = Normalizer.normalize(constraint, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();

                for (Curso curso : cursosCopia) {
                    if (Normalizer.normalize(curso.getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)
                            || Normalizer.normalize(curso.getCarrera().getNombre(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)
                            || Normalizer.normalize(curso.getCodigo() + "", Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase().contains(filterPattern)) {
                        filteredList.add(curso);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cursos.clear();
            cursos.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public CursosModel() {
        this.cursos = new ArrayList<>();
        this.cursosCopia = new ArrayList<>();
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public ArrayList<Curso> getCursosCopia() {
        return cursosCopia;
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

    @Override
    public Filter getFilter() {
        return filter;
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
