/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudiantesModel extends Observable {

    public EstudiantesModel() {
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
    
    
}
