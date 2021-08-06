package ku.cs.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class RegisterPageController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordPasswordField;
    @FXML private Button registerBtn;
    @FXML private Label loginLabel;
    @FXML private Label closeLabel;

    @FXML
    public void clickCloseLabel(Event event) {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToLoginPage() throws IOException {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void mouseEnteredLoginLabel(MouseEvent mouseEvent) {
        loginLabel.setStyle("-fx-text-fill: #7597fd");
        System.out.println("Mouse entered login label");
    }

    @FXML
    private void mouseExitedLoginLabel(MouseEvent mouseEvent) {
        loginLabel.setStyle("-fx-text-fill: #575757");
        System.out.println("mouse exited login label");
    }
}
