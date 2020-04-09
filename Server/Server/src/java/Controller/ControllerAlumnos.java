/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Alumno;
import Logic.Carrera;
import Logic.Curso;
import Logic.Usuario;
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
    
    public JSONObject getAlumno(String cedulaString) throws SQLException, JSONException{
        int cedula = Integer.parseInt(cedulaString);
        JSONObject jsonResponse = new JSONObject();
        model.setAlumno(model.getDb().getAlumno(cedula));
        model.setCursosMatriculados(model.getDb().getCusosMatriculados(cedula));
        Carrera carrera = model.getDb().getCarrera(model.getAlumno().getCarrera());
        
        jsonResponse.put("success", true);
        JSONObject jsonAlumno = new JSONObject();
            jsonAlumno.put("cedula", model.getAlumno().getCedula());
            jsonAlumno.put("nombre", model.getAlumno().getNombre());
            jsonAlumno.put("telefono", model.getAlumno().getTelefono());
            jsonAlumno.put("email", model.getAlumno().getEmail());
            jsonAlumno.put("carrera", carrera.getNombre());
        jsonResponse.put("alumno", jsonAlumno);
        
        JSONArray array = new JSONArray();
        for(Curso curso: model.getCursosMatriculados()){
        JSONObject jsonCurso = new JSONObject();
        jsonCurso.put("codigo", curso.getCodigo());
        jsonCurso.put("nombre", curso.getNombre());
        jsonCurso.put("creditos", curso.getCreditos());
        jsonCurso.put("horasSemanales", curso.getHorasSemanales());
        jsonCurso.put("carrera", carrera.getNombre());
        array.put(jsonCurso);
        }
    
        jsonResponse.put("cursos", array);
        return jsonResponse;
    }
    
    public JSONObject insertarAlumno(int cedula, String nombre, int telefono, String email,int carrera, String usuario, String clave) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        model.setAlumno(new Alumno(cedula,nombre,telefono, email,carrera,usuario));
        model.setUsuario(new Usuario(usuario, clave, "Alumno"));
        model.getDb().insertUsuario(model.getUsuario());
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
        model.setAlumno(new Alumno(cedula,nombre,telefono, email,carrera,""));
        model.getDb().actualizarAlumno(model.getAlumno());
        jsonResponse.put("success", true);
        return jsonResponse;
    }
    
}
