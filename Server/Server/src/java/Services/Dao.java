/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Logic.Alumno;
import Logic.Carrera;
import Logic.Curso;
import Logic.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jaalf
 */
public class Dao {
    private RelDataBase db;

    public Dao() {
        db = new RelDataBase();
    }

    public void addCarrera() throws Exception {
        String sql = "insert into carrera (nombre) "
                + "values('%s')";
        sql = String.format(sql,"informatica");

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Usuario ya existe");
        }
    }

    public Usuario getUsuario(String username, String clave) throws SQLException {
        String sql = "call getUsuario('%s','%s')";
        sql = String.format(sql,username,clave);

        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return new Usuario(rs.getString("username"),rs.getString("clave"),rs.getString("permiso"));
    }

    public ArrayList<Curso> getCursos() throws SQLException {
    
        String sql = "call getCursos()";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Curso> cursos = new ArrayList<>();

        while (rs.next()) {
            cursos.add(new Curso(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horas_semanales"),
                    rs.getInt("carrera")
            ));
        }

        if (cursos.isEmpty()) {
            System.out.println("No existen cursos");
        }

        return cursos;
    }
    
    public void insertCurso(Curso curso){
        String sql = "call insertCurso('%s', %d, %d,%d)";
        sql = String.format(sql, curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales(),curso.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }

    public void eliminarCurso(int codigo) {
        String sql = "call deleteCurso(%d)";
        sql = String.format(sql, codigo);

        if (db.executeUpdate(sql) == 0) {}
    }

    public void actualizarCurso(Curso curso) {
    String sql = "call updateCurso(%d, '%s', %d, %d,%d)";
        sql = String.format(sql,curso.getCodigo(), curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales(),curso.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }
    

    public Carrera getCarrera(int codigo) throws SQLException {
        String sql = "call getCarrera(%d)";
        sql = String.format(sql,codigo);

        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return new Carrera(codigo,rs.getString("nombre"));
    }

    public ArrayList<Carrera> getCarreras() throws SQLException {
        String sql = "call getCarreras()";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Carrera> carreras = new ArrayList<>();

        while (rs.next()) {
            carreras.add(new Carrera(
                    rs.getInt("codigo"),
                    rs.getString("nombre")
            ));
        }

        if (carreras.isEmpty()) {
            System.out.println("No existen cursos");
        }

        return carreras;
    }

    public ArrayList<Alumno> getAlumnos() throws SQLException {
        String sql = "call getAlumnos()";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        while (rs.next()) {
            alumnos.add(new Alumno(
                    rs.getInt("cedula"),
                    rs.getString("nombre"),
                    rs.getInt("telefono"),
                    rs.getString("email"),
                    rs.getInt("carrera")
            ));
        }

        if (alumnos.isEmpty()) {
            System.out.println("No existen cursos");
        }

        return alumnos;
    }
    
    public void insertAlumno(Alumno alumno){
        String sql = "call insertAlumno(%d, '%s', %d, '%s',%d)";
        sql = String.format(sql,alumno.getCedula(), alumno.getNombre(), alumno.getTelefono(), alumno.getEmail(),alumno.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }

    public void eliminarAlumno(int cedula) {
        String sql = "call deleteAlumno(%d)";
        sql = String.format(sql, cedula);

        if (db.executeUpdate(sql) == 0) {}
    }

    public void actualizarAlumno(Alumno alumno) {
    String sql = "call updateAlumno(%d, '%s', %d, '%s',%d)";
        sql = String.format(sql, alumno.getCedula(),alumno.getNombre(), alumno.getTelefono(), alumno.getEmail(),alumno.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }
}
