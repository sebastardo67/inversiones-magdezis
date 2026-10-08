package org.java.magdezis.inversiones.examen.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.java.magdezis.inversiones.examen.config.ConnectionDb;
import org.java.magdezis.inversiones.examen.model.Rol;
import org.java.magdezis.inversiones.examen.model.Usuario;

public class RegisterRepository {

    public List<Rol> listarRoles() throws SQLException {
        String sql = "SELECT id_rol, nombre_rol FROM roles ORDER BY nombre_rol";
        List<Rol> roles = new ArrayList<>();

        try (Connection con = ConnectionDb.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                roles.add(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
            }
        }
        return roles;
    }

    public boolean existeEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";

        try (Connection con = ConnectionDb.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (id_usuario, nombre, apellido, email, password_hash, id_rol) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionDb.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getIdUsuario());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPasswordHash());
            ps.setInt(6, u.getIdRol());
            ps.executeUpdate();
        }
    }
}