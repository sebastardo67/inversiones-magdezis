package org.java.magdezis.inversiones.examen.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.java.magdezis.inversiones.examen.dto.request.LoginRequest;
import org.java.magdezis.inversiones.examen.dto.response.LoginResponse;
import org.java.magdezis.inversiones.examen.service.LoginService;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;
    @FXML private Button btnIngresar;

    private final LoginService loginService = new LoginService();

    @FXML
    private void onIngresar() {
        lblMensaje.setText("");

        LoginResponse respuesta = loginService.login(
                new LoginRequest(txtEmail.getText(), txtPassword.getText()));

        if (!respuesta.isExito()) {
            lblMensaje.setText(respuesta.getMensaje());
            txtPassword.clear();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
            Parent root = loader.load();

            HomeController home = loader.getController();
            home.setUsuario(respuesta);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/home.css").toExternalForm());

            Stage stage = (Stage) btnIngresar.getScene().getWindow();
            stage.setTitle("Inicio");
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            lblMensaje.setText("Error al cargar la pantalla principal.");
            e.printStackTrace();
        }
    }
}