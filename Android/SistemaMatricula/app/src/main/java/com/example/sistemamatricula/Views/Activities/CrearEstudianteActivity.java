package com.example.sistemamatricula.Views.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemamatricula.Controllers.CrearEstudianteController;
import com.example.sistemamatricula.Models.CrearEstudianteModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ActivityCrearEstudianteBinding;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Logic.Carrera;
import Logic.Estudiante;
import Logic.Usuario;

public class CrearEstudianteActivity extends AppCompatActivity implements Observer {

    private ActivityCrearEstudianteBinding binding;
    private CrearEstudianteController crearEstudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearEstudianteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        crearEstudianteController = new CrearEstudianteController(new CrearEstudianteModel(), this);
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

    public void onCrearEstudianteClick(View view) {
        LoginActivity.hideKeyboardFrom(this, view);

        if (!camposVacios() && !camposInvalidos()) {
            new AlertDialog.Builder(this)
                    .setView(R.layout.cargando)
                    .setCancelable(false)
                    .create()
                    .show();

            crearEstudianteController.postEstudianteRequest(new Estudiante(binding.etCedula.getText().toString(),
                    binding.etNombre.getText().toString(),
                    binding.etTelefono.getText().toString(),
                    binding.etEmail.getText().toString(),
                    new Carrera(0, binding.spnCarreras.getSelectedItem().toString()),
                    null,
                    new Usuario(binding.etUsuario.getText().toString(), binding.etClave.getText().toString(), "Estudiante")));
        }
    }

    public boolean camposVacios() {
        boolean vacio = false;

        if (binding.etNombre.getText().length() == 0) {
            binding.etlNombre.setError("Debes escribir un nombre");
            vacio = true;
        }

        if (binding.etCedula.getText().length() == 0) {
            binding.etlCedula.setError("Debes escribir una cédula");
            vacio = true;
        }

        if (binding.etEmail.getText().length() == 0) {
            binding.etlEmail.setError("Debes escribir un email");
            vacio = true;
        }

        if (binding.etTelefono.getText().length() == 0) {
            binding.etlTelefono.setError("Debes escribir un teléfono");
            vacio = true;
        }

        if (binding.etUsuario.getText().length() == 0) {
            binding.etlUsuario.setError("Debes escribir un usuario");
            vacio = true;
        }

        if (binding.etClave.getText().length() == 0) {
            binding.etlClave.setError("Debes escribir una clave");
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

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText().toString().trim()).matches()) {
            binding.etlEmail.setError("Debes escribir un email válido");
            invalido = true;
        }

        if (!EditarEstudianteActivity.numeroValido(binding.etTelefono.getText().toString())) {
            binding.etlTelefono.setError("Debes escribir un teléfono válido");
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

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etEmail.length() == 0) {
                    binding.etlEmail.setError("Debes escribir un email");
                    return;
                }

                binding.etlEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etTelefono.length() == 0) {
                    binding.etlTelefono.setError("Debes escribir un teléfono");
                    return;
                }

                binding.etlTelefono.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etCedula.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etCedula.length() == 0) {
                    binding.etlCedula.setError("Debes escribir una cédula");
                    return;
                }

                binding.etlCedula.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etUsuario.length() == 0) {
                    binding.etlUsuario.setError("Debes escribir un usuario");
                    return;
                }

                binding.etlUsuario.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etClave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etClave.length() == 0) {
                    binding.etlClave.setError("Debes escribir una clave");
                    return;
                }

                binding.etlClave.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        CrearEstudianteModel crearEstudianteModel = ((CrearEstudianteModel) o);

        switch ((String) arg) {
            case "Carreras obtenidas":
                ArrayList<String> carreras = new ArrayList<>();
                carreras.add("-- SELECCIONAR --");

                for (Carrera carrera : crearEstudianteModel.getCarreras()) {
                    carreras.add(carrera.getNombre());
                }

                binding.spnCarreras.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, carreras));
                break;

            case "Estudiante creado":
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
