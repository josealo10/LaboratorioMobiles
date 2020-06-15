package com.example.matricula.Views.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matricula.Controllers.EditarCursoController;
import com.example.matricula.Logic.Curso;
import com.example.matricula.Models.EditarCursoModel;
import com.example.matricula.databinding.ActivityEditarCursoBinding;

import java.util.Observable;
import java.util.Observer;

public class EditarCursoActivity extends AppCompatActivity implements Observer {

    private ActivityEditarCursoBinding binding;
    private EditarCursoModel editarCursoModel;
    private EditarCursoController editarCursoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarCursoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etCreditos.setOnEditorActionListener((v, actionId, event) -> {
            onEditarCursoClick(v);
            return true;
        });

        editarCursoModel = new EditarCursoModel((Curso) getIntent().getSerializableExtra("curso"));
        editarCursoController = new EditarCursoController(editarCursoModel, this);
        setValues((Curso) getIntent().getSerializableExtra("curso"));
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
        if (!camposVacios() && !camposInvalidos()) {
            editarCursoModel.getCurso().setNombre(binding.etNombre.getText().toString());
            editarCursoModel.getCurso().setCreditos(Integer.parseInt(binding.etCreditos.getText().toString()));
            editarCursoController.putCurso();
        }
    }

    public boolean camposVacios() {
        limpiarErrores();
        boolean vacio = false;

        if (binding.etNombre.getText().length() == 0) {
            binding.etNombre.setError("Debes escribir un nombre");
            vacio = true;
        }

        if (binding.etCreditos.getText().length() == 0) {
            binding.etCreditos.setError("Debes escribir una cantidad de créditos");
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

        return invalido;
    }

    public void limpiarErrores() {
        binding.etNombre.setError(null);
        binding.etCreditos.setError(null);
    }

    public void setValues(Curso curso) {
        binding.etNombre.setText(curso.getNombre());
        binding.etCreditos.setText(curso.getCreditos() + "");
        binding.etCodigo.setText(curso.getCodigo());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("Curso actualizado")) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
