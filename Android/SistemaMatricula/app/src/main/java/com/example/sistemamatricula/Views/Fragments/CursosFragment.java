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

import com.example.sistemamatricula.Controllers.CursosController;
import com.example.sistemamatricula.Models.CursosModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.Views.Activities.CrearCursoActivity;
import com.example.sistemamatricula.Views.Activities.EditarCursoActivity;
import com.example.sistemamatricula.databinding.FragmentCursosBinding;
import com.google.android.material.snackbar.Snackbar;

import Logic.Curso;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class CursosFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentCursosBinding binding;
    private CursosController cursosController;
    private int position;
    private boolean deshacer = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCursosBinding.inflate(inflater);

        binding.fabAgregarCurso.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), CrearCursoActivity.class), 1));
        binding.srlCursos.setOnRefreshListener(this);

        cursosController = new CursosController(new CursosModel(), this);

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
                        Curso curso = cursosController.deleteItem(position);

                        Snackbar snackbar = Snackbar.make(binding.rvCursos, "Curso eliminado", Snackbar.LENGTH_LONG)
                                .setAction("Deshacer", v -> {
                                    cursosController.addItem(position, curso);
                                    deshacer = true;
                                }).setActionTextColor(Color.RED);

                        ((TextView) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text)).setTextColor(Color.WHITE);

                        snackbar.addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                if (!deshacer) {
                                    cursosController.deleteCursoRequest(curso.getCodigo());
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
                        startActivityForResult(new Intent(getContext(), EditarCursoActivity.class).putExtra("curso", cursosController.getCursosModel().getCursos().get(position)), 1);
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

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvCursos);

        binding.rvCursos.setAdapter(cursosController.getCursosModel());
        binding.rvCursos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvCursos.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        cursosController.getCursosRequest();

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && (resultCode == RESULT_OK || resultCode == RESULT_CANCELED)) {
            cursosController.getCursosRequest();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        cursosController.getCursosRequest();
        binding.srlCursos.setRefreshing(false);
    }
}
