package org.java.magdezis.inversiones.examen.dto.request;

public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private int idRol;

    public RegisterRequest(String nombre, String apellido, String email,
                           String password, int idRol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getIdRol() { return idRol; }
}