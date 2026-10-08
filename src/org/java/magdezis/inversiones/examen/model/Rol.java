package org.java.magdezis.inversiones.examen.model;

public class Rol {

    private int idRol;
    private String nombreRol;

    public Rol(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() { return idRol; }
    public String getNombreRol() { return nombreRol; }

    // El ComboBox muestra este texto
    @Override
    public String toString() {
        return nombreRol;
    }
}