package com.example.sistemamatricula.Controllers;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemamatricula.Models.CursosModel;
import com.example.sistemamatricula.Models.LoginModel;
import com.example.sistemamatricula.Utils.RequestQueue;
import com.example.sistemamatricula.Views.Fragments.CursosFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import Logic.Carrera;
import Logic.Curso;

public class CursosController {

    private final String url = LoginModel.URL_SERVIDOR + "ServeletCursos";
    private CursosModel cursosModel;
    private CursosFragment cursosFragment;

    public CursosController(CursosModel cursosModel, CursosFragment cursosFragment) {
        this.cursosModel = cursosModel;
        this.cursosFragment = cursosFragment;
    }

    public CursosModel getCursosModel() {
        return cursosModel;
    }

    public void getCursosRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                cursosModel.getCursos().clear();
                cursosModel.getCursosCopia().clear();

                parseJSONGet(response);

                cursosModel.getCursosCopia().addAll(cursosModel.getCursos());

                cursosModel.notifyDataSetChanged();

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(cursosFragment.getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void addItem(int position, Curso curso) {
        cursosModel.getCursos().add(position, curso);
        cursosModel.notifyItemInserted(position);
    }

    public void deleteCursoRequest(int codigo) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url + "?codigo=" + codigo, null, response -> {
            try {
                parseJSONDelete(response);

            } catch (Exception ignored) {
            }

        }, null);

        RequestQueue.getInstance(cursosFragment.getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public Curso deleteItem(int position) {
        Curso curso = cursosModel.getCursos().get(position);

        cursosModel.getCursos().remove(position);
        cursosModel.notifyItemRemoved(position);

        return curso;
    }

    private void parseJSONGet(JSONObject response) throws Exception {
        if (response.getBoolean("success")) {
            JSONArray cursos = response.getJSONArray("cursos");

            for (int i = 0; i < cursos.length(); ++i) {
                cursosModel.getCursos().add(new Curso(cursos.getJSONObject(i).getInt("codigo"),
                        cursos.getJSONObject(i).getInt("creditos"),
                        cursos.getJSONObject(i).getInt("horasSemanales"),
                        cursos.getJSONObject(i).getString("nombre"),
                        new Carrera(cursos.getJSONObject(i).getInt("carrera"), cursos.getJSONObject(i).getString("nombreCarrera"))));
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
