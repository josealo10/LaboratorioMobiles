package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Models.EditarCursoModel;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Utils.RequestQueue;
import com.example.sistemamatricula.Views.Activities.EditarCursoActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import Logic.Carrera;

public class EditarCursoController {

    private final String urlCarreras = LoginModel.URL_SERVIDOR + "ServeletCarrera";
    private final String urlCurso = LoginModel.URL_SERVIDOR + "ServeletCursos";
    private EditarCursoModel editarCursoModel;
    private EditarCursoActivity editarCursoActivity;

    public EditarCursoController(EditarCursoModel editarCursoModel, EditarCursoActivity editarCursoActivity) {
        this.editarCursoModel = editarCursoModel;
        this.editarCursoActivity = editarCursoActivity;
        this.editarCursoModel.addObserver(this.editarCursoActivity);
    }

    public void getCarrerasRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlCarreras, null, response -> {
            try {
                editarCursoModel.getCarreras().clear();

                parseJSONGet(response);

                editarCursoModel.notificar("Carreras obtenidas");

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(editarCursoActivity).addToRequestQueue(jsonObjectRequest);
    }

    public void putCursoRequest(int codigo, String nombre, int creditos, int horas, String carrera) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("codigo", codigo);
            jsonObject.put("nombre", nombre);
            jsonObject.put("creditos", creditos);
            jsonObject.put("horasSemanales", horas);

            for (Carrera c : editarCursoModel.getCarreras()) {
                if (c.getNombre().equals(carrera)) {
                    jsonObject.put("carrera", c.getCodigo());
                    break;
                }
            }

        } catch (Exception ignored) {
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, urlCurso, jsonObject, response -> {
            try {
                parseJSONPut(response);

                editarCursoModel.notificar("Curso actualizado");

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(editarCursoActivity).addToRequestQueue(jsonObjectRequest);
    }

    private void parseJSONGet(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            JSONArray carreras = response.getJSONArray("carreras");

            for (int i = 0; i < carreras.length(); ++i) {
                editarCursoModel.getCarreras().add(new Carrera(carreras.getJSONObject(i).getInt("codigo"),
                        carreras.getJSONObject(i).getString("nombre")));
            }
        } else {
            throw new Exception();
        }
    }

    private void parseJSONPut(JSONObject response) throws Exception {
        if (!response.getBoolean("success")) {
            throw new Exception();
        }
    }
}