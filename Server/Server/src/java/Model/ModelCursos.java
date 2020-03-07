/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Logic.Curso;
import Services.Dao;
import java.util.ArrayList;

/**
 *
 * @author jaalf
 */
public class ModelCursos {
    private ArrayList<Curso> cursos;
    private Dao db;
    
    public ModelCursos(){
        db = new Dao();
        cursos = new ArrayList<>();
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public Dao getDb() {
        return db;
    }
    
    
    
}
