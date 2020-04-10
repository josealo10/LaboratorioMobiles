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

    private EstudianteModel estudianteModel;
    private EstudianteView estudianteView;
    private String url = "http://localhost:8080/Server/ServeletAlumnos?cedula=%s";

    public EstudianteController(EstudianteModel estudianteModel, EstudianteView estudianteView) {
        this.estudianteModel = estudianteModel;
        this.estudianteView = estudianteView;
        this.estudianteView.setController(this);
        this.estudianteModel.addObserver(this.estudianteView);
    }

    public void setVisible(boolean visible, int cedula) {
        estudianteModel.getEstudiante().setCedula(cedula + "");
        this.estudianteView.setVisible(visible);
    }
    
    public EstudianteModel getEstudiantesModel() {
        return estudianteModel;
    }

    public void getEstudianteRequest() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(String.format(url, estudianteModel.getEstudiante().getCedula())).openConnection();
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
            estudianteModel.getEstudiante().setTelefono(jsonResponse.getJSONObject("alumno").getInt("telefono") + "");
            estudianteModel.getEstudiante().setCarrera(new Carrera(0, jsonResponse.getJSONObject("alumno").getString("carrera")));
            estudianteModel.getEstudiante().setNombre(jsonResponse.getJSONObject("alumno").getString("nombre"));
            estudianteModel.getEstudiante().setEmail(jsonResponse.getJSONObject("alumno").getString("email"));

            JSONArray cursos = jsonResponse.getJSONArray("cursos");

            for (int i = 0; i < cursos.length(); ++i) {
                estudianteModel.getEstudiante().getCursos().add(new Curso(cursos.getJSONObject(i).getInt("codigo"),
                        cursos.getJSONObject(i).getInt("creditos"), 
                        cursos.getJSONObject(i).getInt("horasSemanales"), 
                        cursos.getJSONObject(i).getString("nombre"),
                        new Carrera(0, cursos.getJSONObject(i).getString("carrera"))));
            }
            
            estudianteModel.notificar();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }
}
