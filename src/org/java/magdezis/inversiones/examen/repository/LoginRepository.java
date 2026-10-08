package org.java.magdezis.inversiones.examen.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.java.magdezis.inversiones.examen.config.ConnectionDb;
import org.java.magdezis.inversiones.examen.model.Usuario;

public class LoginRepository {

    private static final String SQL_BUSCAR_POR_EMAIL =
            "SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.password_hash, "
            + "       u.id_rol, r.nombre_rol "
            + "FROM usuarios u "
            + "INNER JOIN roles r ON u.id_rol = r.id_rol "
            + "WHERE u.email = ?";

    public Optional<Usuario> buscarPorEmail(String email) throws SQLException {
        try (Connection con = ConnectionDb.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR_POR_EMAIL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Usuario(
                            rs.getString("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol")));
                }
            }
        }
        return Optional.empty();
    }
}