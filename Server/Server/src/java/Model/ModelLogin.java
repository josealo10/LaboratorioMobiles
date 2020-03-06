/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Services.Dao;

/**
 *
 * @author jaalf
 */
public class ModelLogin {
    private String username;
    private String password;
    private Dao db;

    public ModelLogin() {
        db = new Dao();
    }

    public ModelLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Dao getDb() {
        return db;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}