package com.example.matricula.Views.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matricula.Controllers.EditarEstudianteController;
import com.example.matricula.Logic.Curso;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Models.EditarEstudianteModel;
import com.example.matricula.R;
import com.example.matricula.databinding.ActivityEditarEstudianteBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class EditarEstudianteActivity extends AppCompatActivity {

    private ActivityEditarEstudianteBinding binding;
    private EditarEstudianteModel editarEstudianteModel;
    private EditarEstudianteController editarEstudianteController;
    private int position;
    private boolean deshacer = false;

    public static boolean numeroValido(String numero) {
        if (numero == null || numero.length() == 0) {
            return false;
        }

        for (char c : numero.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarEstudianteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etEdad.setOnEditorActionListener((v, actionId, event) -> {
            onEditarEstudianteClick(v);
            return true;
        });

        editarEstudianteModel = new EditarEstudianteModel((Estudiante) getIntent().getSerializableExtra("estudiante"));
        editarEstudianteController = new EditarEstudianteController(editarEstudianteModel, this);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    Curso curso = editarEstudianteController.deleteItem(position);

                    Snackbar snackbar = Snackbar.make(binding.rvCursosMatriculados, "Curso desmatriculado", Snackbar.LENGTH_LONG)
                            .setAction("Deshacer", v -> {
                                editarEstudianteController.addItem(position, curso);
                                deshacer = true;
                            }).setActionTextColor(Color.RED);

                    ((TextView) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text)).setTextColor(Color.WHITE);

                    snackbar.addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            if (!deshacer) {
                                editarEstudianteController.deleteMatriculado(curso.getCodigo());
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
                        .addSwipeLeftActionIcon(R.drawable.ic_elimiar)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvCursosMatriculados);

        binding.rvCursosMatriculados.setAdapter(editarEstudianteModel);
        binding.rvCursosMatriculados.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvCursosMatriculados.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        try {
            editarEstudianteController.getCursos();

        } catch (Exception ignored) {
        }

        setValues(editarEstudianteModel.getEstudiante());
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setIcon(R.drawable.ic_salir)
                .setTitle("Cerrar sesión")
                .setMessage("¿Deseas cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                })
                .create()
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_salir) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }

        return false;
    }

    public void onEditarEstudianteClick(View view) {
        if (!camposVacios() && !camposInvalidos()) {
            editarEstudianteModel.getEstudiante().setNombre(binding.etNombre.getText().toString());
            editarEstudianteModel.getEstudiante().setApellidos(binding.etApellidos.getText().toString());
            editarEstudianteModel.getEstudiante().setEdad(Integer.parseInt(binding.etEdad.getText().toString()));
            editarEstudianteController.putEstudiante();

            Toasty.success(this, "¡Editado exitosamente!", Toasty.LENGTH_LONG, true).show();
        }
    }

    public void onMatricularClick(View view) {
        if (binding.spnCursos.getSelectedItemPosition() != 0) {
            try {
                editarEstudianteController.postMatriculado(binding.etCedula.getText().toString(), binding.spnCursos.getSelectedItem().toString().substring(0, binding.spnCursos.getSelectedItem().toString().indexOf("-") - 1));

            } catch (Exception e) {
                Toasty.error(this, e.getMessage(), Toasty.LENGTH_LONG, true).show();
            }

        } else {
            Toasty.error(this, "Debes seleccionar un curso", Toasty.LENGTH_LONG, true).show();
        }
    }

    public boolean camposVacios() {
        limpiarErrores();
        boolean vacio = false;

        if (binding.etNombre.getText().length() == 0) {
            binding.etNombre.setError("Debes escribir un nombre");
            vacio = true;
        }

        if (binding.etApellidos.getText().length() == 0) {
            binding.etApellidos.setError("Debes escribir un apellido");
            vacio = true;
        }

        if (binding.etEdad.getText().length() == 0) {
            binding.etEdad.setError("Debes escribir una edad");
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidos() {
        boolean invalido = false;

        if (!EditarEstudianteActivity.numeroValido(binding.etEdad.getText().toString())) {
            binding.etEdad.setError("Debes escribir una edad válido");
            invalido = true;
        }

        return invalido;
    }

    public void limpiarErrores() {
        binding.etNombre.setError(null);
        binding.etApellidos.setError(null);
        binding.etEdad.setError(null);
    }

    public void setValues(Estudiante estudiante) {
        binding.etCedula.setText(estudiante.getCedula());
        binding.etNombre.setText(estudiante.getNombre());
        binding.etApellidos.setText(estudiante.getApellidos());
        binding.etEdad.setText(estudiante.getEdad() + "");

        ArrayList<String> cursos = new ArrayList<>();

        cursos.add("-- SELECCIONE --");

        for (Curso curso : editarEstudianteModel.getCursos()) {
            cursos.add(curso.getCodigo() + " - " + curso.getNombre());
        }

        binding.spnCursos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cursos));

        try {
            editarEstudianteController.getCursosMatriculados();

        } catch (Exception e) {
            Toasty.info(this, e.getMessage(), Toasty.LENGTH_LONG, true).show();
        }
    }
}