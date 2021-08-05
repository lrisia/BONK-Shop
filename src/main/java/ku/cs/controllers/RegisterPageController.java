package ku.cs.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.App;

import java.io.IOException;

public class RegisterPageController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmPasswordTextField;
    @FXML private Button registerBtn;
    @FXML private Label loginLabel;
    @FXML private Label closeLabel;

    @FXML
    public void clickCloseLabel(Event event) {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login_page");
    }
}
