/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Alumno;
import Model.ModelAlumnos;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jaalf
 */
public class ControllerAlumnos {
    
    private ModelAlumnos model;

    public ControllerAlumnos() {
        model = new ModelAlumnos();
    }

    public JSONObject getAlumnos() throws JSONException, SQLException {
        JSONObject jsonResponse = new JSONObject();
        JSONArray array = new JSONArray();

        model.setAlumnos(model.getDb().getAlumnos());

        for(Alumno alumno: model.getAlumnos()){
            JSONObject jsonAlumno = new JSONObject();
            jsonAlumno.put("cedula", alumno.getCedula());
            jsonAlumno.put("nombre", alumno.getNombre());
            jsonAlumno.put("telefono", alumno.getTelefono());
            jsonAlumno.put("email", alumno.getEmail());
            jsonAlumno.put("carrera", alumno.getCarrera());//model.getDb().getCarrera(curso.getCarrera()).getNombre());
            array.put(jsonAlumno);
        }

        jsonResponse.put("success", true);
        jsonResponse.put("estudiantes", array);

        return jsonResponse;
    }
    
    public JSONObject insertarAlumno(int cedula, String nombre, int telefono, String email,int carrera) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        model.setAlumno(new Alumno(cedula,nombre,telefono, email,carrera));
        model.getDb().insertAlumno(model.getAlumno());
        jsonResponse.put("success", true);
        return jsonResponse;
    }

    public JSONObject eliminarAlumno(String cedulaString) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        int cedula = Integer.parseInt(cedulaString);;
        model.getDb().eliminarAlumno(cedula);
        jsonResponse.put("success", true);
        return jsonResponse;
    }

    public JSONObject actualizarAlumno(int cedula, String nombre, int telefono, String email,int carrera) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        model.setAlumno(new Alumno(cedula,nombre,telefono, email,carrera));
        model.getDb().actualizarAlumno(model.getAlumno());
        jsonResponse.put("success", true);
        return jsonResponse;
    }
    
}
