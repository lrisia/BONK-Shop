package ku.cs.controllers.verify;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;

import com.github.saacsos.FXRouter;
import ku.cs.services.RecordedAccount;

public class LoginPageController {
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordField;
    @FXML private Label closeLabel;
    @FXML private Label notificationLabel;
    @FXML private Label headerLabel;
    @FXML private ImageView loginImageView;

    private RecordedAccount recordedAccount = new RecordedAccount();
    private String registerSuccessful;

    @FXML
    public void initialize() {
        String registerSuccessful = (String) FXRouter.getData();
        if (registerSuccessful != null){
            headerLabel.setText(registerSuccessful);
            notificationLabel.setText("เริ่มช็อปปิ้งกันเลย!");
        }
//        String path = getClass().getResource("images/verify/login.png").toExternalForm();
//        loginImageView.setImage(new Image(path));
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
        String userName = inputUsernameTextField.getText();
        String password = inputPasswordField.getText();
        if (recordedAccount.checkUsernameAlreadyHave(userName, password)) {
            try {
                FXRouter.goTo("main");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } else {
            notificationLabel.setText("Username หรือรหัสผ่านไม่ถูกต้อง");
            notificationLabel.setStyle("-fx-text-fill: #f61e1e");
        }
    }
}
