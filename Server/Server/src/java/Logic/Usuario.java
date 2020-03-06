/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author jaalf
 */
public class Usuario {
    private String username;
    private String clave;
    private String Permiso;

    public Usuario(String username, String clave, String Permiso) {
        this.username = username;
        this.clave = clave;
        this.Permiso = Permiso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPermiso() {
        return Permiso;
    }

    public void setPermiso(String Permiso) {
        this.Permiso = Permiso;
    }
    
    
}
