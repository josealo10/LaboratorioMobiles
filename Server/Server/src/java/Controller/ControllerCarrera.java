/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Carrera;
import Model.ModelCarrera;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jaalf
 */
public class ControllerCarrera {

    private ModelCarrera model;
    
    public ControllerCarrera(){
        model = new ModelCarrera();
    }
    public JSONObject getCarreras() throws JSONException, SQLException {
        JSONObject jsonResponse = new JSONObject();
        JSONArray array = new JSONArray();
        
        model.setCarreras(model.getDb().getCarreras());
        
        for(Carrera carrera: model.getCarreras()){
        JSONObject jsonCurso = new JSONObject();
        jsonCurso.put("codigo", carrera.getCodigo());
        jsonCurso.put("nombre", carrera.getNombre());
        array.put(jsonCurso);
        }
        
        jsonResponse.put("success", true);
        jsonResponse.put("carreras", array);
        
        return jsonResponse;
    }
    
}
