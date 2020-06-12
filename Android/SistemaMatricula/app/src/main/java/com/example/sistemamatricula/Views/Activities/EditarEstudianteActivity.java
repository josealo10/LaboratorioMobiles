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

import com.example.sistemamatricula.Controllers.EditarEstudianteController;
import com.example.sistemamatricula.Models.EditarEstudianteModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ActivityEditarEstudianteBinding;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Logic.Carrera;
import Logic.Estudiante;

public class EditarEstudianteActivity extends AppCompatActivity implements Observer {

    private ActivityEditarEstudianteBinding binding;
    private EditarEstudianteController editarEstudianteController;

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

        editarEstudianteController = new EditarEstudianteController(new EditarEstudianteModel((Estudiante) getIntent().getExtras().get("estudiante")), this);
        editarEstudianteController.getCarrerasRequest();

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

    public void onEditarEstudianteClick(View view) {
        LoginActivity.hideKeyboardFrom(this, view);

        if (!camposVacios() && !camposInvalidos()) {
            new AlertDialog.Builder(this)
                    .setView(R.layout.cargando)
                    .setCancelable(false)
                    .create()
                    .show();

            editarEstudianteController.putEstudianteRequest(((Estudiante) getIntent().getExtras().get("estudiante")).getCedula(), binding.etNombre.getText().toString(), binding.etEmail.getText().toString(), binding.etTelefono.getText().toString(), binding.spnCarreras.getSelectedItem().toString());
        }
    }

    public boolean camposVacios() {
        boolean vacio = false;

        if (binding.etNombre.getText().length() == 0) {
            binding.etlNombre.setError("Debes escribir un nombre");
            vacio = true;
        }

        if (binding.etEmail.getText().length() == 0) {
            binding.etlEmail.setError("Debes escribir un email");
            vacio = true;
        }

        if (binding.etTelefono.getText().length() == 0) {
            binding.etlTelefono.setError("Debes escribir un telefono");
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

        if (!numeroValido(binding.etTelefono.getText().toString())) {
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
    }

    public void setValues(Estudiante estudiante) {
        binding.etNombre.setText(estudiante.getNombre());
        binding.etEmail.setText(estudiante.getEmail());
        binding.etTelefono.setText(estudiante.getTelefono());

        for (int i = 0; i < binding.spnCarreras.getCount(); ++i) {
            if (binding.spnCarreras.getItemAtPosition(i).equals(estudiante.getCarrera().getNombre())) {
                binding.spnCarreras.setSelection(i);
                return;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        EditarEstudianteModel editarEstudianteModel = ((EditarEstudianteModel) o);

        switch ((String) arg) {
            case "Carreras obtenidas":
                ArrayList<String> carreras = new ArrayList<>();

                for (Carrera carrera : editarEstudianteModel.getCarreras()) {
                    carreras.add(carrera.getNombre());
                }

                binding.spnCarreras.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, carreras));

                setValues(editarEstudianteModel.getEstudiante());
                break;

            case "Estudiante actualizado":
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}