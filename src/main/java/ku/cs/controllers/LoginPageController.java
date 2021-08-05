package ku.cs.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import ku.cs.App;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController {

    @FXML private Button loginBtn;
    @FXML private Button switchToRegisterBtn;
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordField;
    @FXML private Label closeLabel;
    @FXML private Label loginMessageLabel; //Username หรือ Password ไม่ถูกต้อง! กรุณาลองใหม่
    @FXML private ImageView myCatImageView;



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File myCatFile = new File("images/พาแมวมาทะเล.jpeg");
        Image myCatImage = new Image(myCatFile.toURI().toString());
        myCatImageView.setImage(myCatImage);
    }

    @FXML
    public void clickCloseLabel(Event event) {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register_page");
    }
}
