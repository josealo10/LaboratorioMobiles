package com.example.sistemamatricula.Logic;

public class Curso {
    
    private int codigo, creditos, horasSemanales;
    private String nombre;

    public Curso(int codigo, int creditos, int horasSemanales, String nombre) {
        this.codigo = codigo;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
    
}
