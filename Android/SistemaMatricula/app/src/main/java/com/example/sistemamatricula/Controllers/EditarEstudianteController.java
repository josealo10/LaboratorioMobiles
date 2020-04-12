package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Utils.RequestQueue;
import com.example.sistemamatricula.Models.EditarEstudianteModel;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Views.Activities.EditarEstudianteActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import Logic.Carrera;

public class EditarEstudianteController {

    private final String urlCarreras = LoginModel.URL_SERVIDOR + "ServeletCarrera";
    private final String urlEstudiante = LoginModel.URL_SERVIDOR + "ServeletAlumnos";
    private EditarEstudianteModel editarEstudianteModel;
    private EditarEstudianteActivity editarEstudianteActivity;

    public EditarEstudianteController(EditarEstudianteModel editarEstudianteModel, EditarEstudianteActivity editarEstudianteActivity) {
        this.editarEstudianteModel = editarEstudianteModel;
        this.editarEstudianteActivity = editarEstudianteActivity;
        this.editarEstudianteModel.addObserver(this.editarEstudianteActivity);
    }

    public void getCarrerasRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlCarreras, null, response -> {
            try {
                editarEstudianteModel.getCarreras().clear();

                parseJSONGet(response);

                editarEstudianteModel.notificar("Carreras obtenidas");

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(editarEstudianteActivity).addToRequestQueue(jsonObjectRequest);
    }

    public void putEstudianteRequest(String cedula, String nombre, String email, String telefono, String carrera) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("cedula", Integer.parseInt(cedula));
            jsonObject.put("nombre", nombre);
            jsonObject.put("telefono", Integer.parseInt(telefono));
            jsonObject.put("email", email);

            for (Carrera c : editarEstudianteModel.getCarreras()) {
                if (c.getNombre().equals(carrera)) {
                    jsonObject.put("carrera", c.getCodigo());
                    break;
                }
            }

        } catch (Exception ignored) {
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, urlEstudiante, jsonObject, response -> {
            try {
                parseJSONPut(response);

                editarEstudianteModel.notificar("Estudiante actualizado");

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(editarEstudianteActivity).addToRequestQueue(jsonObjectRequest);
    }

    private void parseJSONGet(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            JSONArray carreras = response.getJSONArray("carreras");

            for (int i = 0; i < carreras.length(); ++i) {
                editarEstudianteModel.getCarreras().add(new Carrera(carreras.getJSONObject(i).getInt("codigo"),
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
