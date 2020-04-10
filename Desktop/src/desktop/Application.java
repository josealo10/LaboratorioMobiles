package desktop;

import Controllers.CursosController;
import Controllers.EstudianteController;
import Controllers.EstudiantesController;
import Controllers.LoginController;
import Models.CursosModel;
import Models.EstudianteModel;
import Models.EstudiantesModel;
import Models.LoginModel;
import Views.CursosView;
import Views.EstudianteView;
import Views.EstudiantesView;
import Views.LoginView;

/**
 *
 * @author Alessandro Fazio
 */
public class Application {

    public static LoginController LOGIN_CONTROLLER;
    public static EstudianteController ESTUDIANTE_CONTROLLER;
    public static CursosController CURSOS_CONTROLLER;
    public static EstudiantesController ESTUDIANTES_CONTROLLER;

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LOGIN_CONTROLLER = new LoginController(new LoginModel(), loginView);
        
        ESTUDIANTE_CONTROLLER = new EstudianteController(new EstudianteModel(), new EstudianteView());
        CURSOS_CONTROLLER = new CursosController(new CursosModel(), new CursosView());
        ESTUDIANTES_CONTROLLER = new EstudiantesController(new EstudiantesModel(), new EstudiantesView ());

        loginView.setVisible(true);
    }
}
