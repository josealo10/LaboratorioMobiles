/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.EstudiantesModel;
import Views.EstudiantesView;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudiantesController {
    
    private EstudiantesModel estudiantesModel;
    private EstudiantesView estudiantesView;

    public EstudiantesController(EstudiantesModel estudiantesModel, EstudiantesView estudiantesView) {
        this.estudiantesModel = estudiantesModel;
        this.estudiantesView = estudiantesView;
        this.estudiantesModel.addObserver(this.estudiantesView);
    }
    
    public void setVisible(boolean visible) {
        this.estudiantesView.setVisible(visible);
    }
     
}
