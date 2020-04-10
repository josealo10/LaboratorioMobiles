package desktop;

import Controllers.CursosController;
import Controllers.EstudianteController;
import Controllers.LoginController;
import Models.CursosModel;
import Models.EstudianteModel;
import Models.LoginModel;
import Views.CursosView;
import Views.EstudianteView;
import Views.LoginView;

/**
 *
 * @author Alessandro Fazio
 */
public class Application {

    public static LoginController LOGIN_CONTROLLER;
    public static EstudianteController ESTUDIANTES_CONTROLLER;
    public static CursosController CURSOS_CONTROLLER;

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LOGIN_CONTROLLER = new LoginController(new LoginModel(), loginView);
        
        ESTUDIANTES_CONTROLLER = new EstudianteController(new EstudianteModel(), new EstudianteView());
        CURSOS_CONTROLLER = new CursosController(new CursosModel(), new CursosView());

        loginView.setVisible(true);
    }
}
