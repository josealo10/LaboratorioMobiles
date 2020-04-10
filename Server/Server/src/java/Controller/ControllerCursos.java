/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Curso;
import Model.ModelCursos;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jaalf
 */
public class ControllerCursos {
    private ModelCursos model;
    
    public ControllerCursos(){
        model = new ModelCursos();
    }

    public JSONObject getCursos() throws JSONException, SQLException {
    JSONObject jsonResponse = new JSONObject();
    JSONArray array = new JSONArray();
    
    model.setCursos(model.getDb().getCursos());
    
    for(Curso curso: model.getCursos()){
        JSONObject jsonCurso = new JSONObject();
        jsonCurso.put("codigo", curso.getCodigo());
        jsonCurso.put("nombre", curso.getNombre());
        jsonCurso.put("creditos", curso.getCreditos());
        jsonCurso.put("horasSemanales", curso.getHorasSemanales());
        jsonCurso.put("carrera", curso.getCarrera());
        jsonCurso.put("nombreCarrera",model.getDb().getCarrera(curso.getCarrera()).getNombre());
        array.put(jsonCurso);
    }
    
    jsonResponse.put("success", true);
    jsonResponse.put("cursos", array);
    
    return jsonResponse;
    }

    public JSONObject insertarCurso(String nombre, int creditos, int horasSemanales,int carrera) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        model.setCurso(new Curso(-1,nombre,creditos, horasSemanales,carrera));
        model.getDb().insertCurso(model.getCurso());
        jsonResponse.put("success", true);
        return jsonResponse;
    }

    public JSONObject eliminarCurso(String codigoString) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        int codigo = Integer.parseInt(codigoString);;
        model.getDb().eliminarCurso(codigo);
        jsonResponse.put("success", true);
        return jsonResponse;
    }

    public JSONObject actualizarCurso(int codigo, String nombre, int creditos, int horasSemanales,int carrera) throws JSONException {
        JSONObject jsonResponse = new JSONObject();
        model.setCurso(new Curso(codigo,nombre,creditos, horasSemanales,carrera));
        model.getDb().actualizarCurso(model.getCurso());
        jsonResponse.put("success", true);
        return jsonResponse;
    }
}
