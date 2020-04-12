package com.example.sistemamatricula.Views.Fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sistemamatricula.Controllers.EstudiantesController;
import com.example.sistemamatricula.Models.EstudiantesModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.Views.Activities.CrearEstudianteActivity;
import com.example.sistemamatricula.Views.Activities.EditarEstudianteActivity;
import com.example.sistemamatricula.databinding.FragmentEstudiantesBinding;
import com.google.android.material.snackbar.Snackbar;

import Logic.Estudiante;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class EstudiantesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentEstudiantesBinding binding;
    private EstudiantesController estudiantesController;
    private int position;
    private boolean deshacer = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEstudiantesBinding.inflate(inflater);

        binding.fabAgregarEstudiante.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), CrearEstudianteActivity.class), 1));
        binding.srlEstudiantes.setOnRefreshListener(this);

        estudiantesController = new EstudiantesController(new EstudiantesModel(), this);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                position = viewHolder.getAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        Estudiante estudiante = estudiantesController.deleteItem(position);

                        Snackbar snackbar = Snackbar.make(binding.rvEstudiantes, "Estudiante eliminado", Snackbar.LENGTH_LONG)
                                .setAction("Deshacer", v -> {
                                    estudiantesController.addItem(position, estudiante);
                                    deshacer = true;
                                }).setActionTextColor(Color.RED);

                        ((TextView) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text)).setTextColor(Color.WHITE);

                        snackbar.addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                if (!deshacer) {
                                    estudiantesController.deleteEstudianteRequest(estudiante.getCedula());
                                }

                                deshacer = false;
                            }

                            @Override
                            public void onShown(Snackbar snackbar) {
                            }
                        });

                        snackbar.show();
                        break;

                    case ItemTouchHelper.RIGHT:
                        startActivityForResult(new Intent(getContext(), EditarEstudianteActivity.class).putExtra("estudiante", estudiantesController.getEstudiantesModel().getEstudiantes().get(position)), 1);
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

        binding.rvEstudiantes.setAdapter(estudiantesController.getEstudiantesModel());
        binding.rvEstudiantes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvEstudiantes.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        estudiantesController.getEstudiantesRequest();

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && (resultCode == RESULT_OK || resultCode == RESULT_CANCELED)) {
            estudiantesController.getEstudiantesRequest();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        estudiantesController.getEstudiantesRequest();
        binding.srlEstudiantes.setRefreshing(false);
    }
}
