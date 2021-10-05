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
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.UserDataSource;

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

    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = (AccountList) FXRouter.getData();

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
        String username = usernameTextField.getText();
        String name = nameTextField.getText();
        String password = passwordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();
        if (name.equals("")) {
            alertLabel.setText("ยังไม่ได้กรอกชื่อ");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (username.equals("")) {
            alertLabel.setText("ยังไม่ได้กรอก Username");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (password.equals("")) {
            alertLabel.setText("ยังไม่ได้กรอกรหัสผ่าน");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (confirmPassword.equals("")) {
            alertLabel.setText("ยังไม่ได้ยืนยันรหัสผ่าน");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (!password.equals(confirmPassword)) {
            alertLabel.setText("รหัสผ่านไม่ตรงกัน");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
            confirmPasswordPasswordField.clear();
        } else if (accountList.canRegister(username)) {
            accountList.registerNewAccount(username, password, name);
            dataSource.writeData(accountList);
            try {
                FXRouter.goTo("login", "สมัครสำเร็จแล้ว");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า login ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } else {
            alertLabel.setText("Username นี้ถูกใช้แล้ว");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
        }
    }
    //―――――――――――――――――――――――――――――――――――――――――――
}
