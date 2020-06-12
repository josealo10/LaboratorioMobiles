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
    private static Dao instance = null;

    private Dao() {
	
	private String getUsuario = "call getUsuario('%s','%s')";
	private String getCursos = "call getCursos()";
	private String insertCurso = "call insertCurso('%s', %d, %d,%d)";
	private String deleteCurso = "call deleteCurso(%d)";
	private String updateCurso = "call updateCurso(%d, '%s', %d, %d,%d)";
	private String getCarrera = "call getCarrera(%d)";
	private String getCarreras = "call getCarreras()";
	private String getAlumnos = "call getAlumnos()";
	private String getAlumno = "call getAlumno(%d)";
	private String getAlumnoWithUsername = "call getAlumnoWithUsername('%s')";
	private String insertAlumno = "call insertAlumno(%d, '%s', %d, '%s',%d, '%s')";
	private String deleteAlumno = "call deleteAlumno(%d)";
	private String updateAlumno = "call updateAlumno(%d, '%s', %d, '%s',%d)";
	private String getCursosMatriculados = "call getCursosMatriculados(%d)";
	private String insertUsuario = "call insertUsuario('%s','%s','%s')";
	
    public Dao() {
        db = new RelDataBase();
    }
    
    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }
        
        return instance;
    }

    public Usuario getUsuario(String username, String clave) throws SQLException {
        String sql = getUsuario;
        sql = String.format(sql,username,clave);

        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return new Usuario(rs.getString("username"),rs.getString("clave"),rs.getString("permiso"));
    }

    public ArrayList<Curso> getCursos() throws SQLException {
    
        String sql = getCursos;
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
        String sql = insertCurso;
        sql = String.format(sql, curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales(),curso.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }

    public void eliminarCurso(int codigo) {
        String sql = deleteCurso;
        sql = String.format(sql, codigo);

        if (db.executeUpdate(sql) == 0) {}
    }

    public void actualizarCurso(Curso curso) {
    String sql = updateCurso;
        sql = String.format(sql,curso.getCodigo(), curso.getNombre(), curso.getCreditos(), curso.getHorasSemanales(),curso.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }
    

    public Carrera getCarrera(int codigo) throws SQLException {
        String sql = getCarrera;
        sql = String.format(sql,codigo);

        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return new Carrera(codigo,rs.getString("nombre"));
    }

    public ArrayList<Carrera> getCarreras() throws SQLException {
        String sql = getCarreras;
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
        String sql = getAlumnos;
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        while (rs.next()) {
            alumnos.add(new Alumno(
                    rs.getInt("cedula"),
                    rs.getString("nombre"),
                    rs.getInt("telefono"),
                    rs.getString("email"),
                    rs.getInt("carrera"),
                    rs.getString("usuario")
            ));
        }

        if (alumnos.isEmpty()) {
            System.out.println("No existen cursos");
        }

        return alumnos;
    }
    
    public Alumno getAlumno(int cedula) throws SQLException{
        String sql = getAlumno;
        sql = String.format(sql,cedula);
        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return new Alumno(
                    rs.getInt("cedula"),
                    rs.getString("nombre"),
                    rs.getInt("telefono"),
                    rs.getString("email"),
                    rs.getInt("carrera"),
                    rs.getString("usuario")
            );
    }
    
    public int getAlumno(String username) throws SQLException{
        String sql = getAlumnoWithUsername;
        sql = String.format(sql,username);
        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return rs.getInt("cedula");
    }
    
    public void insertAlumno(Alumno alumno){
        String sql = insertAlumno;
        sql = String.format(sql,alumno.getCedula(), alumno.getNombre(), 
                alumno.getTelefono(), alumno.getEmail(),
                alumno.getCarrera(), alumno.getUsuario());

        if (db.executeUpdate(sql) == 0) {}
    }

    public void eliminarAlumno(int cedula) {
        String sql = deleteAlumno;
        sql = String.format(sql, cedula);

        if (db.executeUpdate(sql) == 0) {}
    }

    public void actualizarAlumno(Alumno alumno) {
    String sql = updateAlumno;
        sql = String.format(sql, alumno.getCedula(),alumno.getNombre(), alumno.getTelefono(), alumno.getEmail(),alumno.getCarrera());

        if (db.executeUpdate(sql) == 0) {}
    }

    public ArrayList<Curso> getCusosMatriculados(int cedula) throws SQLException {
        String sql = getCursosMatriculados;
        sql = String.format(sql, cedula);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Curso> cursos = new ArrayList<>();

        while (rs.next()) {
            cursos.add(new Curso(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("creditos"),
                    rs.getInt("horas_semanales"),
                    -1
            ));
        }

        if (cursos.isEmpty()) {
            System.out.println("No existen cursos");
        }

        return cursos;
    }

    public void insertUsuario(Usuario usuario) {
        String sql = insertUsuario;
        sql = String.format(sql,usuario.getUsername(),usuario.getClave(),usuario.getPermiso());

        if (db.executeUpdate(sql) == 0) {}
    }
}
