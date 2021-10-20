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
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.UserDataSource;

public class LoginPageController {
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordField;
    @FXML private Label closeLabel;
    @FXML private Label notificationLabel;
    @FXML private Label headerLabel;
    @FXML private Label mainLabel;
    @FXML private ImageView loginImageView;

    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();
    private Effect effect = new Effect();
    public static String getUsername;

    @FXML
    public void initialize() {
        String registerSuccessful = (String) FXRouter.getData();
        mainLabel.setText("เข้าสู่ระบบเพื่อยืนยันตัวตน");
        if (registerSuccessful != null){
            headerLabel.setText(registerSuccessful);
            mainLabel.setText("เริ่มช็อปปิ้งกันเลย!");
        }
    }

    @FXML
    public void clickCloseLabel(Event event) {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void switchToRegister() throws IOException {
        try {
            FXRouter.goTo("register", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void loginAccept(ActionEvent actionEvent) throws IOException {
        String username = inputUsernameTextField.getText();
        String password = inputPasswordField.getText();
        if (!accountList.canLogin(username, password)) {
            mainLabel.setText("ชื่อผู้ใช้ หรือ รหัสผ่านไม่ถูกต้อง");
            mainLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (accountList.searchAccountByUsername(username).gotBanned()) {
            mainLabel.setText("บัญชีของคุณถูกระงับการใช้งาน โปรดติดต่อผู้ดูแล");
            mainLabel.setStyle("-fx-text-fill: #f61e1e");
            dataSource.writeData(accountList);
        } else {
            try {
                getUsername = username;
                dataSource.writeData(accountList);
                FXRouter.goTo("main", new Shop(accountList.searchAccountByUsername(username)));
            } catch (IOException e) {
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
    }
}
