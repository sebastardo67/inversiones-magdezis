package org.java.magdezis.inversiones.examen.dto.response;

public class LoginResponse {

    private boolean exito;
    private String mensaje;
    private String idUsuario;
    private String nombreCompleto;
    private String email;
    private int idRol;
    private String nombreRol;

    public LoginResponse() {
    }

    public static LoginResponse error(String mensaje) {
        LoginResponse r = new LoginResponse();
        r.exito = false;
        r.mensaje = mensaje;
        return r;
    }

    public static LoginResponse ok(String idUsuario, String nombreCompleto,
                                   String email, int idRol, String nombreRol) {
        LoginResponse r = new LoginResponse();
        r.exito = true;
        r.mensaje = "Inicio de sesión exitoso";
        r.idUsuario = idUsuario;
        r.nombreCompleto = nombreCompleto;
        r.email = email;
        r.idRol = idRol;
        r.nombreRol = nombreRol;
        return r;
    }

    public boolean isExito() { return exito; }
    public String getMensaje() { return mensaje; }
    public String getIdUsuario() { return idUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEmail() { return email; }
    public int getIdRol() { return idRol; }
    public String getNombreRol() { return nombreRol; }
}