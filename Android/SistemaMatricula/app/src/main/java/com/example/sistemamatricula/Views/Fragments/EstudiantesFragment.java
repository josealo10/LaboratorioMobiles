package com.example.sistemamatricula.Views.Fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemamatricula.Controllers.EstudiantesController;
import com.example.sistemamatricula.Models.EstudiantesModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.Views.Activities.EditarEstudianteActivity;
import com.example.sistemamatricula.databinding.FragmentEstudiantesBinding;
import com.google.android.material.snackbar.Snackbar;

import Logic.Estudiante;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class EstudiantesFragment extends Fragment implements EstudiantesModel.OnEstudianteClickListener {

    private FragmentEstudiantesBinding binding;
    private EstudiantesModel estudiantesModel;
    private EstudiantesController estudiantesController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEstudiantesBinding.inflate(inflater);

        binding.fabAgregarEstudiante.setOnClickListener(v -> {

        });

        this.estudiantesModel = new EstudiantesModel(this);
        this.estudiantesController = new EstudiantesController(estudiantesModel, this);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        Estudiante estudiante = estudiantesController.eliminarEstudiante(position);

                        Snackbar snackbar = Snackbar.make(binding.rvEstudiantes, "Estudiante eliminado", Snackbar.LENGTH_LONG)
                                .setAction("Deshacer", v -> estudiantesController.agregarEstudiante(position, estudiante)).setActionTextColor(Color.RED);

                        ((TextView) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text)).setTextColor(Color.WHITE);

                        snackbar.show();
                        break;

                    case ItemTouchHelper.RIGHT:
                        startActivity(new Intent(getContext(), EditarEstudianteActivity.class));
                        new Handler().postDelayed(() -> estudiantesModel.notifyDataSetChanged(), 500);
                        break;
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(Color.RED)
                        .addSwipeRightBackgroundColor(Color.DKGRAY)
                        .addSwipeLeftActionIcon(R.drawable.ic_elimiar)
                        .addSwipeRightActionIcon(R.drawable.ic_editar)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvEstudiantes);

        binding.rvEstudiantes.setAdapter(estudiantesModel);
        binding.rvEstudiantes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvEstudiantes.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }

    @Override
    public void onEstudianteClick(Estudiante estudiante) {

    }
}
