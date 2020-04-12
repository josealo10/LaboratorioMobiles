package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Utils.RequestQueue;
import com.example.sistemamatricula.Models.EstudiantesModel;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Views.Fragments.EstudiantesFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import Logic.Carrera;
import Logic.Estudiante;

public class EstudiantesController {

    private final String url = LoginModel.URL_SERVIDOR + "ServeletAlumnos";
    private EstudiantesModel estudiantesModel;
    private EstudiantesFragment estudiantesFragment;

    public EstudiantesController(EstudiantesModel estudiantesModel, EstudiantesFragment estudiantesFragment) {
        this.estudiantesModel = estudiantesModel;
        this.estudiantesFragment = estudiantesFragment;
    }

    public EstudiantesModel getEstudiantesModel() {
        return estudiantesModel;
    }

    public void getEstudiantesRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                estudiantesModel.getEstudiantes().clear();

                parseJSONGet(response);

                estudiantesModel.notifyDataSetChanged();

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(estudiantesFragment.getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void addItem(int position, Estudiante estudiante) {
        estudiantesModel.getEstudiantes().add(position, estudiante);
        estudiantesModel.notifyItemInserted(position);
    }

    public void deleteEstudianteRequest(String cedula) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url + "?cedula=" + cedula, null, response -> {
            try {
                parseJSONDelete(response);

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(estudiantesFragment.getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public Estudiante deleteItem(int position) {
        Estudiante estudiante = estudiantesModel.getEstudiantes().get(position);

        estudiantesModel.getEstudiantes().remove(position);
        estudiantesModel.notifyItemRemoved(position);

        return estudiante;
    }

    private void parseJSONGet(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            JSONArray estudiantes = response.getJSONArray("estudiantes");

            for (int i = 0; i < estudiantes.length(); ++i) {
                estudiantesModel.getEstudiantes().add(new Estudiante(estudiantes.getJSONObject(i).getInt("cedula") + "",
                        estudiantes.getJSONObject(i).getString("nombre"),
                        estudiantes.getJSONObject(i).getInt("telefono") + "",
                        estudiantes.getJSONObject(i).getString("email"),
                        new Carrera(estudiantes.getJSONObject(i).getInt("carrera"), estudiantes.getJSONObject(i).getString("nombreCarrera")),
                        null, null));
            }
        } else {
            throw new Exception();
        }
    }

    private void parseJSONDelete(JSONObject response) throws Exception {
        if (!response.getBoolean("success")) {
            throw new Exception();
        }
    }
}
