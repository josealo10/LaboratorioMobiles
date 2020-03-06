/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Usuario;
import Model.ModelLogin;
import java.io.PrintWriter;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jaalf
 */
public class ControllerLogin {
    private ModelLogin model;
    

    public ControllerLogin() {
        model = new ModelLogin();
    }
    
    private Usuario getUsuario(String username, String password) throws SQLException{
        model.setPassword(password);
        model.setUsername(username);
        return model.getDb().getUsuario(model.getUsername(),model.getPassword());
    }

    public JSONObject getLoginResponse(String username, String clave) throws SQLException, JSONException {
       JSONObject jsonResponse = new JSONObject();
       Usuario usuario = this.getUsuario(username, clave);
        System.out.println("permiso");
        System.out.println(usuario.getPermiso());
       if (usuario.getPermiso().equals("")){
           jsonResponse.put("success", false);
       }else{
           jsonResponse.put("success", true);
           jsonResponse.put("permiso", usuario.getPermiso());
       }
       
       return jsonResponse;
    }

    
    
}