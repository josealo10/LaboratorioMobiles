package com.example.matricula.Views.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matricula.Controllers.LoginController;
import com.example.matricula.R;
import com.example.matricula.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginController loginController;

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setBackgroundDrawableResource(R.drawable.background);

        binding.etClave.setOnEditorActionListener((v, actionId, event) -> {
            onLoginClick(v);
            return true;
        });

        loginController = new LoginController(this);
    }

    public void onLoginClick(View view) {
        hideKeyboardFrom(this, view);

        if (!camposVacios()) {
            try {
                if (loginController.getUsuario(binding.etUsuario.getText().toString(), binding.etClave.getText().toString()).equals("estudiante")) {
                    startActivity(new Intent(this, EditarEstudianteActivity.class).putExtra("estudiante", loginController.getEstudiante(binding.etUsuario.getText().toString())));

                } else {
                    startActivity(new Intent(this, NavigationDrawerActivity.class));
                }

                finish();

            } catch (Exception e) {
                binding.etUsuario.setError(e.getMessage());
                binding.etClave.setError(e.getMessage());
            }
        }
    }

    public void onCrearCuentaClick(View view) {
        startActivity(new Intent(this, CrearCuentaActivity.class));
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