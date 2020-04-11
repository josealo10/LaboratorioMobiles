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
import androidx.databinding.DataBindingUtil;

import com.example.sistemamatricula.Controllers.LoginController;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.R;
import com.example.sistemamatricula.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        this.loginModel = new LoginModel();
        this.loginController = new LoginController(this.loginModel, this);

        textChanged();
    }

    public void textChanged() {
        binding.etUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    binding.etlUsuario.setError("Debes escribir un usuario");
                } else {
                    binding.etlUsuario.setError(null);
                }
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
                if (s.length() == 0) {
                    binding.etlClave.setError("Debes escribir una contraseña");
                } else {
                    binding.etlClave.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

        return emptyFields;
    }

    public void clearErrors() {
        binding.etlUsuario.setError(null);
        binding.etlClave.setError(null);
    }

    public void onLoginClick(View v) {
        hideKeyboardFrom(this, v);

        if (emptyFields()) {
            return;
        }

        try {
            if (loginController.login(binding.etUsuario.getText().toString(), binding.etClave.getText().toString()).equals("Administrador")) {
                startActivity(new Intent(this, NavigationDrawerActivity.class));
                finish();

            } else {

            }

        } catch (Exception exception) {
            binding.etlUsuario.setError(exception.getMessage());
            binding.etlClave.setError(exception.getMessage());
        }
    }
}
