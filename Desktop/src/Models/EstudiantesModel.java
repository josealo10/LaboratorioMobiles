package Models;

import Logic.Curso;
import Logic.Estudiante;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudiantesModel extends Observable {

    private Estudiante estudiante;
    private DefaultTableModel cursosTableModel;

    public EstudiantesModel() {
        this.estudiante = new Estudiante();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public DefaultTableModel getCursosTableModel() {
        return cursosTableModel;
    }

    public void setCursosTableModel(DefaultTableModel cursosTableModel) {
        this.cursosTableModel = cursosTableModel;
    }
    
    public void notificar() {
        setChanged();
        notifyObservers();
    }

    public void llenarTabla() {
        for (Curso curso : estudiante.getCursos()) {
            Object[] o = new Object[]{curso.getCodigo(), curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales()};
            cursosTableModel.addRow(o);
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}
