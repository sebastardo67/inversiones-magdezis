package org.java.magdezis.inversiones.examen.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.java.magdezis.inversiones.examen.dto.request.RegisterRequest;
import org.java.magdezis.inversiones.examen.dto.response.RegisterResponse;
import org.java.magdezis.inversiones.examen.model.Rol;
import org.java.magdezis.inversiones.examen.model.Usuario;
import org.java.magdezis.inversiones.examen.repository.RegisterRepository;
import org.java.magdezis.inversiones.examen.security.jbcrypt.BCrypt;

public class RegisterService {

    private static final String EMAIL_REGEX = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";

    private final RegisterRepository repository = new RegisterRepository();

    public List<Rol> obtenerRoles() throws SQLException {
        return repository.listarRoles();
    }

    public RegisterResponse registrar(RegisterRequest r) {
        if (vacio(r.getNombre()) || vacio(r.getApellido())
                || vacio(r.getEmail()) || vacio(r.getPassword())) {
            return RegisterResponse.error("Completa todos los campos.");
        }
        if (!r.getEmail().trim().matches(EMAIL_REGEX)) {
            return RegisterResponse.error("El correo electrónico no es válido.");
        }
        if (r.getPassword().length() < 6) {
            return RegisterResponse.error("La contraseña debe tener al menos 6 caracteres.");
        }

        try {
            String email = r.getEmail().trim().toLowerCase();

            if (repository.existeEmail(email)) {
                return RegisterResponse.error("Ya existe un usuario con ese correo.");
            }

            Usuario nuevo = new Usuario(
                    UUID.randomUUID().toString(),
                    r.getNombre().trim(),
                    r.getApellido().trim(),
                    email,
                    BCrypt.hashpw(r.getPassword(), BCrypt.gensalt()),
                    r.getIdRol(),
                    null);

            repository.insertar(nuevo);
            return RegisterResponse.ok("Usuario registrado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            return RegisterResponse.error("Error al guardar en la base de datos.");
        }
    }

    private boolean vacio(String s) {
        return s == null || s.isBlank();
    }
}