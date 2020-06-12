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

import com.example.sistemamatricula.Controllers.CrearCursoController;
import com.example.sistemamatricula.Models.CrearCursoModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ActivityCrearCursoBinding;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Logic.Carrera;
import Logic.Curso;

public class CrearCursoActivity extends AppCompatActivity implements Observer {

    private ActivityCrearCursoBinding binding;
    private CrearCursoController crearEstudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearCursoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        crearEstudianteController = new CrearCursoController(new CrearCursoModel(), this);
        crearEstudianteController.getCarrerasRequest();

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

    public void onCrearCursoClick(View view) {
        LoginActivity.hideKeyboardFrom(this, view);

        if (!camposVacios() && !camposInvalidos()) {
            new AlertDialog.Builder(this)
                    .setView(R.layout.cargando)
                    .setCancelable(false)
                    .create()
                    .show();

            crearEstudianteController.postCursoRequest(new Curso(0,
                    Integer.parseInt(binding.etCreditos.getText().toString()),
                    Integer.parseInt(binding.etHoras.getText().toString()),
                    binding.etNombre.getText().toString(),
                    new Carrera(0, binding.spnCarreras.getSelectedItem().toString())));
        }
    }

    public boolean camposVacios() {
        boolean vacio = false;

        if (binding.etNombre.getText().length() == 0) {
            binding.etlNombre.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etCreditos.getText().length() == 0) {
            binding.etlCreditos.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etHoras.getText().length() == 0) {
            binding.etlHoras.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.spnCarreras.getSelectedItemPosition() == 0) {
            binding.tvCarrera.setError("Este campo no puede estar vacío");
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

    @Override
    public void update(Observable o, Object arg) {
        CrearCursoModel crearCursoModel = ((CrearCursoModel) o);

        switch ((String) arg) {
            case "Carreras obtenidas":
                ArrayList<String> carreras = new ArrayList<>();
                carreras.add("-- SELECCIONAR --");

                for (Carrera carrera : crearCursoModel.getCarreras()) {
                    carreras.add(carrera.getNombre());
                }

                binding.spnCarreras.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, carreras));
                break;

            case "Curso creado":
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
