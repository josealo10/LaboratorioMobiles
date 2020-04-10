/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Logic.Usuario;
import Models.LoginModel;
import Views.AdministradorView;
import Views.LoginView;
import static desktop.Application.ESTUDIANTES_CONTROLLER;
import static desktop.Application.ESTUDIANTE_CONTROLLER;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.Response;
import org.json.JSONObject;

/**
 *
 * @author Alessandro Fazio
 */
public class LoginController {

    private LoginModel loginModel;
    private LoginView loginView;
    private String url = "http://localhost:8080/Server/ServeletLogin";

    public LoginController(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.loginView.setController(this);
        this.loginModel.addObserver(this.loginView);
    }

    public void login(String usuario, String clave) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        JSONObject request = new JSONObject();
        request.put("username", usuario);
        request.put("clave", clave);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = request.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            if (jsonResponse.getString("permiso").equals("Alumno")) {
                this.loginModel.setUsuario(new Usuario(usuario, clave, jsonResponse.getString("permiso")));
                loginView.setVisible(false);
                ESTUDIANTE_CONTROLLER.setVisible(true, jsonResponse.getInt("cedula"));
                
            } else if (jsonResponse.getString("permiso").equals("Administrador")) {
                this.loginModel.setUsuario(new Usuario(usuario, clave, jsonResponse.getString("permiso")));
                loginView.setVisible(false);
                new AdministradorView().setVisible(true);
            }

        } else {
            throw new Exception(jsonResponse.getString("error"));
        }
    }
}
