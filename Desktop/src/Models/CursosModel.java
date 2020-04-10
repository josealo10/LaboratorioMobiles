package Models;

import Logic.Carrera;
import Logic.Curso;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alessandro Fazio
 */
public class CursosModel extends Observable {

    private ArrayList<Curso> cursos;
    private ArrayList<Carrera> carreras;
    private DefaultTableModel cursosTableModel;

    public CursosModel() {
        this.cursos = new ArrayList<>();
        this.carreras = new ArrayList<>();
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

    public DefaultTableModel getCursosTableModel() {
        return cursosTableModel;
    }

    public void setCursosTableModel(DefaultTableModel cursosTableModel) {
        this.cursosTableModel = cursosTableModel;
    }

    public void notificar(String arg) {
        setChanged();
        notifyObservers(arg);
    }

    public void llenarTabla() {
        cursosTableModel.setRowCount(0);
        
        for (Curso curso : cursos) {
            Object[] o = new Object[]{curso.getCodigo(), curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales(), curso.getCarrera().getNombre()};
            cursosTableModel.addRow(o);
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}
