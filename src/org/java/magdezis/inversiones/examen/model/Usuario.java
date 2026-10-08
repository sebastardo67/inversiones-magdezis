package org.java.magdezis.inversiones.examen.model;

public class Usuario {

    private String idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String passwordHash;
    private int idRol;
    private String nombreRol; // viene del JOIN con la tabla roles

    public Usuario() {
    }

    public Usuario(String idUsuario, String nombre, String apellido, String email,
                   String passwordHash, int idRol, String nombreRol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.passwordHash = passwordHash;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}