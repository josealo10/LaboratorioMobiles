/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jaalf
 */
public class ExceptionManager {
    
    static public void manageJsonError(JSONObject jsonResponse) throws JSONException{
        jsonResponse.put("success", false);
        jsonResponse.put("Error", "error al pacear json");
    }
    
    static public void manageBDError(JSONObject jsonResponse) throws JSONException{
        jsonResponse.put("success", false);
        jsonResponse.put("Error", "error en la base de datos");
    }
}
