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
        jsonCurso.put("carrera", curso.getCarrera());//model.getDb().getCarrera(curso.getCarrera()).getNombre());
        array.put(jsonCurso);
    }
    
    jsonResponse.put("success", true);
    jsonResponse.put("cursos", array);
    
    return jsonResponse;
    }
    
}
