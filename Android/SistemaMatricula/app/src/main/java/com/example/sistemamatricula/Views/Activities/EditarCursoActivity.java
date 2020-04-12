package com.example.sistemamatricula.Views.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sistemamatricula.Controllers.EditarCursoController;
import com.example.sistemamatricula.Models.CrearCursoModel;
import com.example.sistemamatricula.Models.EditarCursoModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ActivityEditarCursoBinding;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Logic.Carrera;
import Logic.Curso;

public class EditarCursoActivity extends AppCompatActivity implements Observer {

    private ActivityEditarCursoBinding binding;
    private EditarCursoController editarCursoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editar_curso);

        editarCursoController = new EditarCursoController(new EditarCursoModel((Curso) getIntent().getExtras().get("curso")), this);
        editarCursoController.getCarrerasRequest();

        onTextChanged();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return false;
    }

    public void onEditarCursoClick(View view) {
        LoginActivity.hideKeyboardFrom(this, view);

        if (!camposVacios() && !camposInvalidos()) {
            new AlertDialog.Builder(this)
                    .setView(R.layout.cargando)
                    .setCancelable(false)
                    .create()
                    .show();

            editarCursoController.putCursoRequest(((Curso) getIntent().getExtras().get("curso")).getCodigo(), binding.etNombre.getText().toString(), Integer.parseInt(binding.etCreditos.getText().toString()), Integer.parseInt(binding.etHoras.getText().toString()), binding.spnCarreras.getSelectedItem().toString());
        }
    }

    public boolean camposVacios() {
        boolean vacio = false;

        if (binding.etNombre.getText().length() == 0) {
            binding.etlNombre.setError("Debes escribir un nombre");
            vacio = true;
        }

        if (binding.etCreditos.getText().length() == 0) {
            binding.etlCreditos.setError("Debes escribir una cantidad de créditos");
            vacio = true;
        }

        if (binding.etHoras.getText().length() == 0) {
            binding.etlHoras.setError("Debes escribir una cantidad de horas");
            vacio = true;
        }

        if (binding.spnCarreras.getSelectedItemPosition() == 0) {
            binding.tvCarrera.setError("");
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidos() {
        boolean invalido = false;

        if (!EditarEstudianteActivity.numeroValido(binding.etCreditos.getText().toString())) {
            binding.etlCreditos.setError("Debes escribir un número válido");
            invalido = true;
        }

        if (!EditarEstudianteActivity.numeroValido(binding.etHoras.getText().toString())) {
            binding.etlHoras.setError("Debes escribir un número válido");
            invalido = true;
        }

        return invalido;
    }

    public void onTextChanged() {
        binding.etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etNombre.length() == 0) {
                    binding.etlNombre.setError("Debes escribir un nombre");
                    return;
                }

                binding.etlNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etCreditos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etCreditos.length() == 0) {
                    binding.etlCreditos.setError("Debes escribir una cantidad de créditos");
                    return;
                }

                binding.etlCreditos.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etHoras.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etHoras.length() == 0) {
                    binding.etlHoras.setError("Debes escribir una cantidad de horas");
                    return;
                }

                binding.etlHoras.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setValues(Curso curso) {
        binding.etNombre.setText(curso.getNombre());
        binding.etCreditos.setText(curso.getCreditos() + "");
        binding.etHoras.setText(curso.getHorasSemanales() + "");

        for (int i = 0; i < binding.spnCarreras.getCount(); ++i) {
            if (binding.spnCarreras.getItemAtPosition(i).equals(curso.getCarrera().getNombre())) {
                binding.spnCarreras.setSelection(i);
                return;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        EditarCursoModel editarCursoModel = ((EditarCursoModel) o);

        switch ((String) arg) {
            case "Carreras obtenidas":
                ArrayList<String> carreras = new ArrayList<>();

                for (Carrera carrera : editarCursoModel.getCarreras()) {
                    carreras.add(carrera.getNombre());
                }

                binding.spnCarreras.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, carreras));

                setValues(editarCursoModel.getCurso());
                break;

            case "Curso actualizado":
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
