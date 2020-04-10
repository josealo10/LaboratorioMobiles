package Models;

import Logic.Curso;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alessandro Fazio
 */
public class CursosModel extends Observable {
    
    private ArrayList<Curso> cursos;

    public CursosModel() {
        this.cursos = new ArrayList<>();
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}
