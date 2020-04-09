package Models;

import Logic.Usuario;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alessandro Fazio
 */
public class LoginModel extends Observable {

    private Usuario usuario;

    public LoginModel() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
}