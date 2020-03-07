/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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

    public Carrera getCarrera(int codigo) throws SQLException {
        String sql = "call getCarrera('%d')";
        sql = String.format(sql,codigo);

        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return new Carrera(codigo,rs.getString("nombre"));
    }
}
