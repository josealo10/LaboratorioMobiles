package com.example.matricula.Views.Fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.matricula.Controllers.EstudiantesController;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Models.EstudiantesModel;
import com.example.matricula.R;
import com.example.matricula.Views.Activities.CrearEstudianteActivity;
import com.example.matricula.databinding.FragmentEstudiantesBinding;
import com.google.android.material.snackbar.Snackbar;

import es.dmoral.toasty.Toasty;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class EstudiantesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private FragmentEstudiantesBinding binding;
    private EstudiantesController estudiantesController;
    private int position;
    private boolean deshacer = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEstudiantesBinding.inflate(inflater);
        setHasOptionsMenu(true);

        binding.fabAgregarEstudiante.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), CrearEstudianteActivity.class), 1));
        binding.srlEstudiantes.setOnRefreshListener(this);

        estudiantesController = new EstudiantesController(new EstudiantesModel(), this);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
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
                                estudiantesController.deleteEstudiante(estudiante.getCedula(), estudiante.getUsuario().getUsuario());
                            }

                            deshacer = false;
                        }

                        @Override
                        public void onShown(Snackbar snackbar) {
                        }
                    });

                    snackbar.show();
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

        try {
            estudiantesController.getEstudiantes();

        } catch (Exception ignored) {
        }

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_buscar, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.nav_buscar).getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && (resultCode == RESULT_OK || resultCode == RESULT_CANCELED)) {
            try {
                estudiantesController.getEstudiantes();

            } catch (Exception ignored) {
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        try {
            estudiantesController.getEstudiantes();

        } catch (Exception ignored) {
        }

        binding.srlEstudiantes.setRefreshing(false);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        estudiantesController.getEstudiantesModel().getFilter().filter(newText);
        return true;
    }
}
