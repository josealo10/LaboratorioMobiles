package com.example.matricula.Views.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matricula.Controllers.CrearCursoController;
import com.example.matricula.Logic.Curso;
import com.example.matricula.Models.CrearCursoModel;
import com.example.matricula.databinding.ActivityCrearCursoBinding;

import java.util.Observable;
import java.util.Observer;

import es.dmoral.toasty.Toasty;

public class CrearCursoActivity extends AppCompatActivity implements Observer {

    private ActivityCrearCursoBinding binding;
    private CrearCursoController crearEstudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearCursoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etCreditos.setOnEditorActionListener((v, actionId, event) -> {
            onCrearCursoClick(v);
            return true;
        });

        crearEstudianteController = new CrearCursoController(new CrearCursoModel(), this);
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
        if (!camposVacios() && !camposInvalidos()) {
            try {
                crearEstudianteController.postCurso(new Curso(binding.etCodigo.getText().toString(),
                        binding.etNombre.getText().toString(),
                        Integer.parseInt(binding.etCreditos.getText().toString())));

            } catch (Exception e) {
                Toasty.error(this, e.getMessage(), Toasty.LENGTH_LONG, true).show();
            }
        }
    }

    public boolean camposVacios() {
        limpiarErrores();
        boolean vacio = false;

        if (binding.etCodigo.getText().length() == 0) {
            binding.etCodigo.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etNombre.getText().length() == 0) {
            binding.etNombre.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etCreditos.getText().length() == 0) {
            binding.etCreditos.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidos() {
        boolean invalido = false;

        if (!EditarEstudianteActivity.numeroValido(binding.etCreditos.getText().toString())) {
            binding.etCreditos.setError("Debes escribir un número válido");
            invalido = true;
        }

        if (binding.etCodigo.getText().toString().length() > 6) {
            binding.etCodigo.setError("Debe ser de menor de 6 caracteres");
            invalido = true;
        }

        return invalido;
    }

    public void limpiarErrores() {
        binding.etCodigo.setError(null);
        binding.etNombre.setError(null);
        binding.etCreditos.setError(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Curso creado")) {
            setResult(RESULT_OK);
            finish();
        }
    }
}