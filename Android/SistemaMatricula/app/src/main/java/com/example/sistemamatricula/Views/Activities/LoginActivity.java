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

import com.example.sistemamatricula.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout etl_username, etl_password;
    private TextInputEditText et_username, et_password;

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etl_username = findViewById(R.id.etl_username);
        etl_password = findViewById(R.id.etl_password);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    etl_username.setError("Debes escribir un usuario");
                } else {
                    etl_username.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    etl_password.setError("Debes escribir una contraseña");
                } else {
                    etl_password.setError(null);
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

        if (et_username.length() == 0) {
            emptyFields = true;
            etl_username.setError("Debes escribir un usuario");
        }

        if (et_password.length() == 0) {
            emptyFields = true;
            etl_password.setError("Debes escribir una contraseña");
        }

        return emptyFields;
    }

    public void clearErrors() {
        etl_username.setError(null);
        etl_password.setError(null);
    }

    public void onLoginClick(View v) {
        hideKeyboardFrom(this, v);
        et_username.setText(et_username.getText().toString().trim());

        if (emptyFields()) {
            return;
        }

        startActivity(new Intent(this, NavigationDrawerActivity.class));
        finish();
    }
}
