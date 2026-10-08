package org.java.magdezis.inversiones.examen.dto.response;

public class RegisterResponse {

    private final boolean exito;
    private final String mensaje;

    private RegisterResponse(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public static RegisterResponse ok(String mensaje) {
        return new RegisterResponse(true, mensaje);
    }

    public static RegisterResponse error(String mensaje) {
        return new RegisterResponse(false, mensaje);
    }

    public boolean isExito() { return exito; }
    public String getMensaje() { return mensaje; }
}