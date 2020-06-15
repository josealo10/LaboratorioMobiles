package com.example.matricula.Views.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matricula.Controllers.CrearCuentaController;
import com.example.matricula.Logic.Usuario;
import com.example.matricula.R;
import com.example.matricula.databinding.ActivityCrearCuentaBinding;

import es.dmoral.toasty.Toasty;

import static com.example.matricula.Views.Activities.LoginActivity.hideKeyboardFrom;

public class CrearCuentaActivity extends AppCompatActivity {

    private ActivityCrearCuentaBinding binding;
    private CrearCuentaController crearCuentaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearCuentaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setBackgroundDrawableResource(R.drawable.background);

        binding.etClave.setOnEditorActionListener((v, actionId, event) -> {
            onCrearCuentaClick(v);
            return true;
        });

        crearCuentaController = new CrearCuentaController(this);
    }

    public void onCrearCuentaClick(View view) {
        hideKeyboardFrom(this, view);
        try {
            if (!camposVacios()) {
                crearCuentaController.postUsuario(new Usuario(binding.etUsuario.getText().toString(), binding.etClave.getText().toString(), "administrador"));
                Toasty.success(this, "¡Cuenta creada exitosamente!", Toasty.LENGTH_LONG, true).show();
                finish();
            }

        } catch (Exception e) {
            binding.etUsuario.setError(e.getMessage());
        }
    }

    public boolean camposVacios() {
        boolean camposVacios = false;
        limpiarErrores();

        if (binding.etUsuario.length() == 0) {
            camposVacios = true;
            binding.etUsuario.setError("Debes escribir un usuario");
        }

        if (binding.etClave.length() == 0) {
            camposVacios = true;
            binding.etClave.setError("Debes escribir una contraseña");
        }

        return camposVacios;
    }

    public void limpiarErrores() {
        binding.etUsuario.setError(null);
        binding.etClave.setError(null);
    }
}