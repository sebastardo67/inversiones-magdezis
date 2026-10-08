package org.magdezis.inversiones.examen.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.java.magdezis.inversiones.examen.dto.request.RegisterRequest;
import org.java.magdezis.inversiones.examen.dto.response.RegisterResponse;
import org.java.magdezis.inversiones.examen.model.Rol;
import org.java.magdezis.inversiones.examen.service.RegisterService;

public class RegisterController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmar;
    @FXML private ComboBox<Rol> cmbRol;
    @FXML private Label lblMensaje;
    @FXML private Button btnRegistrar;

    private final RegisterService registerService = new RegisterService();

    @FXML
    private void initialize() {
        try {
            List<Rol> roles = registerService.obtenerRoles();
            cmbRol.getItems().setAll(roles);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarError("No se pudieron cargar los roles.");
            btnRegistrar.setDisable(true);
        }
    }

    @FXML
    private void onRegistrar() {
        if (!txtPassword.getText().equals(txtConfirmar.getText())) {
            mostrarError("Las contraseñas no coinciden.");
            return;
        }

        Rol rol = cmbRol.getValue();
        if (rol == null) {
            mostrarError("Selecciona un rol.");
            return;
        }

        RegisterRequest request = new RegisterRequest(
                txtNombre.getText(),
                txtApellido.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                rol.getIdRol());

        RegisterResponse respuesta = registerService.registrar(request);

        if (respuesta.isExito()) {
            mostrarExito(respuesta.getMensaje());
            limpiarCampos();
        } else {
            mostrarError(respuesta.getMensaje());
        }
    }

    @FXML
    private void onVolver() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

        Stage stage = (Stage) btnRegistrar.getScene().getWindow();
        stage.setTitle("Iniciar sesión");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmar.clear();
        cmbRol.getSelectionModel().clearSelection();
    }

    private void mostrarError(String mensaje) {
        lblMensaje.getStyleClass().setAll("label", "error");
        lblMensaje.setText(mensaje);
    }

    private void mostrarExito(String mensaje) {
        lblMensaje.getStyleClass().setAll("label", "exito");
        lblMensaje.setText(mensaje);
    }
}