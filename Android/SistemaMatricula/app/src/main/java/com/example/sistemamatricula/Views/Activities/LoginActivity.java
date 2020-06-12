package com.example.sistemamatricula.Views.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemamatricula.Controllers.LoginController;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.databinding.ActivityLoginBinding;

import java.util.Observable;
import java.util.Observer;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements Observer {

    private ActivityLoginBinding binding;
    private LoginModel loginModel;
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

        this.loginModel = new LoginModel();
        this.loginController = new LoginController(this.loginModel, this);

        onTextChanged();
    }

    public boolean emptyFields() {
        boolean emptyFields = false;
        clearErrors();

        if (binding.etUsuario.length() == 0) {
            emptyFields = true;
            binding.etlUsuario.setError("Debes escribir un usuario");
        }

        if (binding.etClave.length() == 0) {
            emptyFields = true;
            binding.etlClave.setError("Debes escribir una contraseña");
        }

        if (binding.etIp.length() == 0) {
            emptyFields = true;
            binding.etlIp.setError("Debes escribir una IP");
        }

        return emptyFields;
    }

    public void clearErrors() {
        binding.etlUsuario.setError(null);
        binding.etlClave.setError(null);
        binding.etlIp.setError(null);
    }

    public void onTextChanged() {
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
                    binding.etlClave.setError("Debes escribir una contraseña");
                    return;
                }

                binding.etlClave.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etIp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etIp.length() == 0) {
                    binding.etlIp.setError("Debes escribir una IP");
                    return;
                }

                binding.etlIp.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onLoginClick(View v) {
        hideKeyboardFrom(this, v);

        if (emptyFields()) {
            return;
        }

        binding.lavCargando.setVisibility(View.VISIBLE);
        loginController.postLoginRequest(binding.etUsuario.getText().toString(), binding.etClave.getText().toString(), binding.etIp.getText().toString());
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((String) arg) {
            case "Estudiante":
                binding.lavCargando.setVisibility(View.INVISIBLE);
                Toasty.error(this, "Los estudiantes no tienen accesso al sistema", Toasty.LENGTH_LONG, true).show();
                break;

            case "Administrador":
                startActivity(new Intent(this, NavigationDrawerActivity.class));
                finish();
                break;

            case "Error login":
                binding.lavCargando.setVisibility(View.INVISIBLE);
                binding.etlUsuario.setError("Usuario o contraseña incorrecta");
                binding.etlClave.setError("Usuario o contraseña incorrecta");
                break;

            case "Error servidor":
                binding.lavCargando.setVisibility(View.INVISIBLE);
                binding.etlIp.setError("IP del servidor incorrecta");
                break;
        }
    }
}
