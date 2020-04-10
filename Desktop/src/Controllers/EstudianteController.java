/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Logic.Carrera;
import Logic.Curso;
import Logic.Usuario;
import Models.EstudianteModel;
import Views.EstudianteView;
import static desktop.Application.ESTUDIANTES_CONTROLLER;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudianteController {

    private EstudianteModel estudiantesModel;
    private EstudianteView estudiantesView;
    private String url = "http://localhost:8080/Server/ServeletAlumnos?cedula=%s";

    public EstudianteController(EstudianteModel estudiantesModel, EstudianteView estudiantesView) {
        this.estudiantesModel = estudiantesModel;
        this.estudiantesView = estudiantesView;
        this.estudiantesView.setController(this);
        this.estudiantesModel.addObserver(this.estudiantesView);
    }

    public void setVisible(boolean visible, int cedula) {
        estudiantesModel.getEstudiante().setCedula(cedula + "");
        this.estudiantesView.setVisible(visible);
    }
    
    public EstudianteModel getEstudiantesModel() {
        return estudiantesModel;
    }

    public void getEstudianteRequest() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(String.format(url, estudiantesModel.getEstudiante().getCedula())).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            estudiantesModel.getEstudiante().setTelefono(jsonResponse.getJSONObject("alumno").getInt("telefono") + "");
            estudiantesModel.getEstudiante().setCarrera(jsonResponse.getJSONObject("alumno").getString("carrera"));
            estudiantesModel.getEstudiante().setNombre(jsonResponse.getJSONObject("alumno").getString("nombre"));
            estudiantesModel.getEstudiante().setEmail(jsonResponse.getJSONObject("alumno").getString("email"));

            JSONArray cursos = jsonResponse.getJSONArray("cursos");

            for (int i = 0; i < cursos.length(); ++i) {
                estudiantesModel.getEstudiante().getCursos().add(new Curso(cursos.getJSONObject(i).getInt("codigo"),
                        cursos.getJSONObject(i).getInt("creditos"), 
                        cursos.getJSONObject(i).getInt("horasSemanales"), 
                        cursos.getJSONObject(i).getString("nombre"),
                        new Carrera(0, cursos.getJSONObject(i).getString("carrera"))));
            }
            
            estudiantesModel.notificar();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }
}
