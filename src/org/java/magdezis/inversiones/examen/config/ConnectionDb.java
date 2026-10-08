package org.java.magdezis.inversiones.examen.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionDb {

    private ConnectionDb() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CredentialsDb.URL,
                CredentialsDb.USER,
                CredentialsDb.PASSWORD);
    }
}   