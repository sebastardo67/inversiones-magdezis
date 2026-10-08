package org.java.magdezis.inversiones.examen.service;

import java.sql.SQLException;
import java.util.Optional;
import org.java.magdezis.inversiones.examen.dto.request.LoginRequest;
import org.java.magdezis.inversiones.examen.dto.response.LoginResponse;
import org.java.magdezis.inversiones.examen.model.Usuario;
import org.java.magdezis.inversiones.examen.repository.LoginRepository;
import org.java.magdezis.inversiones.examen.security.jbcrypt.BCrypt;

public class LoginService {

    private static final String MSG_CREDENCIALES = "Correo o contraseña incorrectos.";

    private final LoginRepository loginRepository = new LoginRepository();

    public LoginResponse login(LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isEmpty()) {
            return LoginResponse.error("Ingresa tu correo y contraseña.");
        }

        Optional<Usuario> encontrado;
        try {
            encontrado = loginRepository.buscarPorEmail(request.getEmail().trim());
        } catch (SQLException e) {
            e.printStackTrace();
            return LoginResponse.error("No se pudo conectar con la base de datos.");
        }

        if (encontrado.isEmpty()) {
            return LoginResponse.error(MSG_CREDENCIALES);
        }

        Usuario usuario = encontrado.get();

        boolean coincide;
        try {
            coincide = BCrypt.checkpw(request.getPassword(), usuario.getPasswordHash());
        } catch (IllegalArgumentException e) {
            coincide = false; // el hash guardado no tiene formato BCrypt
        }

        if (!coincide) {
            return LoginResponse.error(MSG_CREDENCIALES);
        }

        return LoginResponse.ok(
                usuario.getIdUsuario(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getIdRol(),
                usuario.getNombreRol());
    }
}