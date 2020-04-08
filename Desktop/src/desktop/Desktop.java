/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import Views.EstudiantesView;
import Views.LoginView;

/**
 *
 * @author Alessandro Fazio
 */
public class Desktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstudiantesView estudiantesView = new EstudiantesView();
        
        LoginView loginView = new LoginView();
        
        loginView.setVisible(true);
    }   
}
