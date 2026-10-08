package org.java.magdezis.inversiones.examen.config;

public final class CredentialsDb {

    // Cambia TU_BASE, el usuario y la clave por los tuyos
    public static final String URL =
            "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    private CredentialsDb() {
    }
}