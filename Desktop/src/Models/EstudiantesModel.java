package Models;

import Logic.Carrera;
import Logic.Curso;
import Logic.Estudiante;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudiantesModel extends Observable {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Carrera> carreras;
    private DefaultTableModel estudiantesTableModel;
    private DefaultTableModel cursosTableModel;

    public EstudiantesModel() {
        this.estudiantes = new ArrayList<>();
        this.carreras = new ArrayList<>();
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

    public DefaultTableModel getEstudiantesTableModel() {
        return estudiantesTableModel;
    }

    public void setEstudiantesTableModel(DefaultTableModel estudiantesTableModel) {
        this.estudiantesTableModel = estudiantesTableModel;
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

    public void llenarTablaEstudiantes() {
        estudiantesTableModel.setRowCount(0);

        for (Estudiante estudiante : estudiantes) {
            Object[] o = new Object[]{estudiante.getCedula(), estudiante.getNombre(), estudiante.getEmail(), estudiante.getTelefono(), estudiante.getCarrera().getNombre()};
            estudiantesTableModel.addRow(o);
        }
    }

    public void llenarTablaCursos(int estudiante) {
        cursosTableModel.setRowCount(0);

        for (Curso curso : estudiantes.get(estudiante).getCursos()) {
            Object[] o = new Object[]{curso.getCodigo(), curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales()};
            cursosTableModel.addRow(o);
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}
