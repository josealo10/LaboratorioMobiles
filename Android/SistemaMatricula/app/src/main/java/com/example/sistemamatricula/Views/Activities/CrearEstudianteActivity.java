package com.example.sistemamatricula.Views.Activities;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crear_estudiante);

        crearEstudianteController = new CrearEstudianteController(new CrearEstudianteModel(), this);
        crearEstudianteController.getCarrerasRequest();
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

    public void onCrearClick(View view) {
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
            binding.etlNombre.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etCedula.getText().length() == 0) {
            binding.etlCedula.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etEmail.getText().length() == 0) {
            binding.etlEmail.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etTelefono.getText().length() == 0) {
            binding.etlTelefono.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etUsuario.getText().length() == 0) {
            binding.etlUsuario.setError("Este campo no puede estar vacío");
            vacio = true;
        }

        if (binding.etClave.getText().length() == 0) {
            binding.etlClave.setError("Este campo no puede estar vacío");
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

    public void setValues(Estudiante estudiante) {
        binding.etNombre.setText(estudiante.getNombre());
        binding.etCedula.setText(estudiante.getCedula());
        binding.etEmail.setText(estudiante.getEmail());
        binding.etTelefono.setText(estudiante.getTelefono());
        binding.etUsuario.setText(estudiante.getUsuario().getUsuario());
        binding.etClave.setText(estudiante.getUsuario().getClave());

        for (int i = 0; i < binding.spnCarreras.getCount(); ++i) {
            if (binding.spnCarreras.getItemAtPosition(i).equals(estudiante.getCarrera().getNombre())) {
                binding.spnCarreras.setSelection(i);
                return;
            }
        }
    }

    private void disableValues() {
        binding.etlNombre.setEnabled(false);
        binding.etlCedula.setEnabled(false);
        binding.etlEmail.setEnabled(false);
        binding.etlTelefono.setEnabled(false);
        binding.etlUsuario.setEnabled(false);
        binding.etlClave.setEnabled(false);
        binding.spnCarreras.setEnabled(false);
        binding.tvCarrera.setTextColor(Color.LTGRAY);
        binding.btnCrear.setVisibility(View.GONE);
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

                if (getIntent().getExtras().getBoolean("solo ver")) {
                    setValues((Estudiante) getIntent().getExtras().get("estudiante"));
                    disableValues();
                    getSupportActionBar().setTitle("Ver estudiante");
                }

                break;

            case "Estudiante creado":
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
