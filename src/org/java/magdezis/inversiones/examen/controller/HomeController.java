package org.java.magdezis.inversiones.examen.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.java.magdezis.inversiones.examen.dto.response.LoginResponse;

public class HomeController {

    @FXML private Label lblBienvenida;
    @FXML private Label lblRol;
    @FXML private Button btnCerrarSesion;

    public void setUsuario(LoginResponse usuario) {
        lblBienvenida.setText("Bienvenido, " + usuario.getNombreCompleto());
        lblRol.setText("Rol: " + usuario.getNombreRol());
    }

    @FXML
    private void onCerrarSesion() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.setTitle("Iniciar sesión");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}