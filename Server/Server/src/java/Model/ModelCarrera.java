/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Logic.Carrera;
import Services.Dao;
import java.util.ArrayList;

/**
 *
 * @author jaalf
 */
public class ModelCarrera {
    private ArrayList<Carrera> carreras;
    private Dao db;

    public ModelCarrera() {
        carreras = new ArrayList<>();
        db = new Dao();
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

    public Dao getDb() {
        return db;
    }    
}
