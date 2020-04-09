/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import Controllers.EstudiantesController;
import Controllers.LoginController;
import Models.EstudiantesModel;
import Models.LoginModel;
import Views.EstudiantesView;
import Views.LoginView;

/**
 *
 * @author Alessandro Fazio
 */
public class Application {

    public static LoginController LOGIN_CONTROLLER;
    public static EstudiantesController ESTUDIANTES_CONTROLLER;

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LOGIN_CONTROLLER = new LoginController(new LoginModel(), loginView);
        
        EstudiantesView estudiantesView = new EstudiantesView(ESTUDIANTES_CONTROLLER);
        ESTUDIANTES_CONTROLLER = new EstudiantesController(new EstudiantesModel(), estudiantesView);

        loginView.setVisible(true);
    }
}
