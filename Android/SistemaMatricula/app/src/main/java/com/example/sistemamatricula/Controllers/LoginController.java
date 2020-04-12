package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Data.RequestQueue;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Views.Activities.LoginActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private LoginModel loginModel;
    private LoginActivity loginActivity;

    public LoginController(LoginModel loginModel, LoginActivity loginActivity) {
        this.loginModel = loginModel;
        this.loginActivity = loginActivity;
        this.loginModel.addObserver(this.loginActivity);
    }

    public void postLoginRequest(String usuario, String clave, String url) {
        LoginModel.URL_SERVIDOR = "http://" + url + ":8080/Server/";

        Map<String, String> params = new HashMap<>();
        params.put("username", usuario);
        params.put("clave", clave);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LoginModel.URL_SERVIDOR + "ServeletLogin", new JSONObject(params), response -> {
            try {
                parseJSON(response);

            } catch (Exception ignored) {
            }

        }, error -> {
            loginModel.notificar("Error servidor");
        });

        RequestQueue.getInstance(loginActivity).addToRequestQueue(jsonObjectRequest);
    }

    private void parseJSON(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            if (response.getString("permiso").equals("Alumno")) {
                loginModel.notificar("Estudiante");

            } else {
                loginModel.notificar("Administrador");
            }

        } else {
            loginModel.notificar("Error login");
        }
    }
}
