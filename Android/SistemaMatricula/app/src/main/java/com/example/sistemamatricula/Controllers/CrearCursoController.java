package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Utils.RequestQueue;
import com.example.sistemamatricula.Models.CrearCursoModel;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Views.Activities.CrearCursoActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import Logic.Carrera;
import Logic.Curso;

public class CrearCursoController {

    private final String urlCarreras = LoginModel.URL_SERVIDOR + "ServeletCarrera";
    private final String urlCurso = LoginModel.URL_SERVIDOR + "ServeletCursos";
    private CrearCursoModel crearCursoModel;
    private CrearCursoActivity crearCursoActivity;

    public CrearCursoController(CrearCursoModel crearCursoModel, CrearCursoActivity crearCursoActivity) {
        this.crearCursoModel = crearCursoModel;
        this.crearCursoActivity = crearCursoActivity;
        this.crearCursoModel.addObserver(this.crearCursoActivity);
    }

    public void getCarrerasRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlCarreras, null, response -> {
            try {
                crearCursoModel.getCarreras().clear();

                parseJSONGet(response);

                crearCursoModel.notificar("Carreras obtenidas");

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(crearCursoActivity).addToRequestQueue(jsonObjectRequest);
    }

    public void postCursoRequest(Curso curso) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre", curso.getNombre());
            jsonObject.put("creditos", curso.getCreditos());
            jsonObject.put("horasSemanales", curso.getHorasSemanales());

            for (Carrera carrera : crearCursoModel.getCarreras()) {
                if (carrera.getNombre().equals(curso.getCarrera().getNombre())) {
                    jsonObject.put("carrera", carrera.getCodigo());
                    break;
                }
            }

        } catch (Exception ignored) {
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlCurso, jsonObject, response -> {
            try {
                parseJSONPost(response);

                crearCursoModel.setCurso(curso);
                crearCursoModel.notificar("Curso creado");

            } catch (Exception ignored) {
            }

        }, error -> {
        });

        RequestQueue.getInstance(crearCursoActivity).addToRequestQueue(jsonObjectRequest);
    }

    private void parseJSONGet(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            JSONArray carreras = response.getJSONArray("carreras");

            for (int i = 0; i < carreras.length(); ++i) {
                crearCursoModel.getCarreras().add(new Carrera(carreras.getJSONObject(i).getInt("codigo"),
                        carreras.getJSONObject(i).getString("nombre")));
            }
        } else {
            throw new Exception();
        }
    }

    private void parseJSONPost(JSONObject response) throws Exception {
        if (!response.getBoolean("success")) {
            throw new Exception();
        }
    }
}
