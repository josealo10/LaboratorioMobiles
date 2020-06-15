package com.example.matricula.Views.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matricula.Controllers.CrearEstudianteController;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Logic.Usuario;
import com.example.matricula.Models.CrearEstudianteModel;
import com.example.matricula.databinding.ActivityCrearEstudianteBinding;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import es.dmoral.toasty.Toasty;

public class CrearEstudianteActivity extends AppCompatActivity implements Observer {

    private ActivityCrearEstudianteBinding binding;
    private CrearEstudianteController crearEstudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearEstudianteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etClave.setOnEditorActionListener((v, actionId, event) -> {
            onCrearEstudianteClick(v);
            return true;
        });

        crearEstudianteController = new CrearEstudianteController(new CrearEstudianteModel(), this);
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

    public void onCrearEstudianteClick(View view) {
        if (!camposVacios() && !camposInvalidos()) {
            try {
                crearEstudianteController.postEstudiante(new Estudiante(binding.etCedula.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etApellidos.getText().toString(),
                        Integer.parseInt(binding.etEdad.getText().toString()),
                        new Usuario(binding.etUsuario.getText().toString(), binding.etClave.getText().toString(), "estudiante"),
                        new ArrayList<>()));

            } catch (Exception e) {
                Toasty.error(this, e.getMessage(), Toasty.LENGTH_LONG, true).show();
            }
        }
    }

    public boolean camposVacios() {
        limpiarErrores();
        boolean vacio = false;

        if (binding.etCedula.getText().length() == 0) {
            binding.etCedula.setError("Debes escribir una cédula");
            vacio = true;
        }

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

        if (binding.etUsuario.getText().length() == 0) {
            binding.etUsuario.setError("Debes escribir un usuario");
            vacio = true;
        }

        if (binding.etClave.getText().length() == 0) {
            binding.etClave.setError("Debes escribir una clave");
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidos() {
        boolean invalido = false;

        if (!EditarEstudianteActivity.numeroValido(binding.etCedula.getText().toString())) {
            binding.etCedula.setError("Debes escribir una cédula válida");
            invalido = true;
        }

        if (!EditarEstudianteActivity.numeroValido(binding.etEdad.getText().toString())) {
            binding.etEdad.setError("Debes escribir una edad válido");
            invalido = true;
        }

        return invalido;
    }

    public void limpiarErrores() {
        binding.etCedula.setError(null);
        binding.etNombre.setError(null);
        binding.etApellidos.setError(null);
        binding.etEdad.setError(null);
        binding.etUsuario.setError(null);
        binding.etClave.setError(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Estudiante creado")) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
