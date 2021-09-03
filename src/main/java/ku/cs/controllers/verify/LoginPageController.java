package ku.cs.controllers.verify;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.*;

import com.github.saacsos.FXRouter;
import javafx.stage.StageStyle;

public class LoginPageController {

    @FXML private Button loginBtn;
    @FXML private Button switchToRegisterBtn;
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordField;
    @FXML private Label closeLabel;
    @FXML private Label notificationLabel;
    @FXML private Label loginMessageLabel; //Username หรือ Password ไม่ถูกต้อง! กรุณาลองใหม่
    @FXML private ImageView myCatImageView;

    public void setNotificationLabelLoginPage(String text) {
        notificationLabel.setText(text);
    }

    @FXML
    public void initialize() {
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
    public void switchToRegister() throws IOException {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void loginAccept(ActionEvent actionEvent) throws IOException {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
