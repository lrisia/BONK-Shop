package ku.cs.controllers.verify;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.Account;
import ku.cs.services.RecordedAccount;

import java.io.IOException;


public class RegisterPageController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordPasswordField;
    @FXML private Label loginLabel;
    @FXML private Label closeLabel;
    @FXML private Label alertLabel;
    @FXML private ImageView registerImageView;

    private RecordedAccount recordedAccount = new RecordedAccount();

    @FXML
    public void initialize() {
//        String path = getClass().getResource("images/verify/register.png").toExternalForm();
//        registerImageView.setImage(new Image(path));
    }

    @FXML //ปุ่มปิด
    public void clickCloseLabel(Event event) {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML //ปุ่มไปหน้า login
    private void switchToLoginPage() {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    //Mouse event บนคำว่า "เข้าสู่ระบบ" ―――――――――――――――――――――――――
    @FXML //เม้าส์อยู่บน "เข้าสู่ระบบ"
    private void mouseEnteredLoginLabel(MouseEvent mouseEvent) {
        loginLabel.setStyle("-fx-text-fill: #7597fd"); //เปลี่ยนสี Label
        System.out.println("Mouse entered login label");
    }

    @FXML //เม้าส์ไม่อยู่บน "เข้าสู่ระบบ"
    private void mouseExitedLoginLabel(MouseEvent mouseEvent) {
        loginLabel.setStyle("-fx-text-fill: #575757"); //เปลี่ยนสีกลับ
        System.out.println("Mouse exited login label");
    }
    //――――――――――――――――――――――――――――――――――――――――――

    //กดสมัครสมาชิก ――――――――――――――――――――――――――――――――――
    @FXML
    private void registerNewAccount() {
        String userName = usernameTextField.getText();
        String name = nameTextField.getText();
        String password = passwordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();
        String recordStatus = recordedAccount.recordRegister(name, userName, password, confirmPassword);
        if (recordStatus.equals("Pass")) {
            try {
                FXRouter.goTo("login", "สมัครสำเร็จแล้ว");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า login ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } else {
            alertLabel.setText(recordStatus);
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
        }
    }
    //―――――――――――――――――――――――――――――――――――――――――――
}