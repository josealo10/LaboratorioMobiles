/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
}
