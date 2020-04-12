package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Utils.RequestQueue;
import com.example.sistemamatricula.Models.CrearEstudianteModel;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Views.Activities.CrearEstudianteActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import Logic.Carrera;
import Logic.Estudiante;

public class CrearEstudianteController {

    private final String urlCarreras = LoginModel.URL_SERVIDOR + "ServeletCarrera";
    private final String urlEstudiante = LoginModel.URL_SERVIDOR + "ServeletAlumnos";
    private CrearEstudianteModel crearEstudianteModel;
    private CrearEstudianteActivity crearEstudianteActivity;

    public CrearEstudianteController(CrearEstudianteModel crearEstudianteModel, CrearEstudianteActivity crearEstudianteActivity) {
        this.crearEstudianteModel = crearEstudianteModel;
        this.crearEstudianteActivity = crearEstudianteActivity;
        this.crearEstudianteModel.addObserver(this.crearEstudianteActivity);
    }

    public void getCarrerasRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlCarreras, null, response -> {
            try {
                crearEstudianteModel.getCarreras().clear();

                parseJSONGet(response);

                crearEstudianteModel.notificar("Carreras obtenidas");

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(crearEstudianteActivity).addToRequestQueue(jsonObjectRequest);
    }

    public void postEstudianteRequest(Estudiante estudiante) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("cedula", Integer.parseInt(estudiante.getCedula()));
            jsonObject.put("nombre", estudiante.getNombre());
            jsonObject.put("telefono", Integer.parseInt(estudiante.getTelefono()));
            jsonObject.put("email", estudiante.getEmail());
            jsonObject.put("usuario", estudiante.getUsuario().getUsuario());
            jsonObject.put("clave", estudiante.getUsuario().getClave());

            for (Carrera carrera : crearEstudianteModel.getCarreras()) {
                if (carrera.getNombre().equals(estudiante.getCarrera().getNombre())) {
                    jsonObject.put("carrera", carrera.getCodigo());
                    break;
                }
            }

        } catch (Exception ignored) {
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlEstudiante, jsonObject, response -> {
            try {
                parseJSONPost(response);

                crearEstudianteModel.setEstudiante(estudiante);
                crearEstudianteModel.notificar("Estudiante creado");

            } catch (Exception ignored) {
            }

        }, error -> {
        });

        RequestQueue.getInstance(crearEstudianteActivity).addToRequestQueue(jsonObjectRequest);
    }

    private void parseJSONGet(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            JSONArray carreras = response.getJSONArray("carreras");

            for (int i = 0; i < carreras.length(); ++i) {
                crearEstudianteModel.getCarreras().add(new Carrera(carreras.getJSONObject(i).getInt("codigo"),
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
