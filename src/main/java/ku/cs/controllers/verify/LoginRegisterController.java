package ku.cs.controllers.verify;

import com.github.saacsos.FXRouter;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.UserDataSource;

import java.io.IOException;

public class LoginRegisterController {
    @FXML private AnchorPane root;
    @FXML private VBox containImageViewVBox;
    @FXML private Label closeLabel;
    @FXML private Label headerLabel;
    @FXML private Label mainLabel;
    @FXML private Label alertLabel;
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordField;
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordPasswordField;
    @FXML private ImageView loginImageView;
    @FXML private ComboBox<String> themeComboBox;

    private Effect effect;
    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();

    @FXML
    public void initialize() {
        effect = new Effect();
        effect.fadeInPage(root);
        readDataFromCsv();
        loginImageView.setImage(new Image(getClass().getResource("/images/verify/login_register.png").toExternalForm()));
        mainLabel.setText("เข้าสู่ระบบเพื่อยืนยันตัวตน");
        initializeComboBox();
    }

    private void initializeComboBox() {
        themeComboBox.getItems().addAll("พื้นฐาน", "อวกาศ", "ฮาโลวีน", "ซากุระ");
        themeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.equals("พื้นฐาน")) FXRouter.setCssStylePath("default.css");
                else if (newValue.equals("อวกาศ")) FXRouter.setCssStylePath("space.css");
                else if (newValue.equals("ฮาโลวีน")) FXRouter.setCssStylePath("halloween.css");
                else if (newValue.equals("ซากุระ")) FXRouter.setCssStylePath("sakura.css");
                refreshPage();
            }
        });
    }

    private void refreshPage() {
        dataSource.writeData(accountList);
        effect.changePage(root, "login_register");
    }

    private void readDataFromCsv() {
        dataSource = new UserDataSource();
        accountList = dataSource.readData();
    }

    @FXML
    public void loginAccept() {
        String username = inputUsernameTextField.getText();
        String password = inputPasswordField.getText();
        if (!accountList.canLogin(username, password)) {

            mainLabel.setText("ชื่อผู้ใช้ หรือรหัสผ่านไม่ถูกต้อง");
            mainLabel.setStyle("-fx-text-fill: invalid-input-notification");

        } else if (accountList.searchAccountByUsername(username).gotBanned()) {
            mainLabel.setText("บัญชีของคุณถูกระงับการใช้งาน โปรดติดต่อผู้ดูแล");
            mainLabel.setStyle("-fx-text-fill: invalid-input-notification");
            dataSource.writeData(accountList);
        } else {
            try {
                dataSource.writeData(accountList);
                FXRouter.goTo("main", new Shop(accountList.searchAccountByUsername(username)));
            } catch (IOException e) {
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void registerNewAccount() {
        String username = usernameTextField.getText();
        String name = nameTextField.getText();
        String password = passwordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();
        if (name.equals("")) {
            alertLabel.setText("ยังไม่ได้กรอกชื่อ");
            alertLabel.setStyle("-fx-text-fill: invalid-input-notification");
        } else if (username.equals("")) {
            alertLabel.setText("ยังไม่ได้กรอก Username");
            alertLabel.setStyle("-fx-text-fill: invalid-input-notification");
        } else if (password.equals("")) {
            alertLabel.setText("ยังไม่ได้กรอกรหัสผ่าน");
            alertLabel.setStyle("-fx-text-fill: invalid-input-notification");
        } else if (confirmPassword.equals("")) {
            alertLabel.setText("ยังไม่ได้ยืนยันรหัสผ่าน");
            alertLabel.setStyle("-fx-text-fill: invalid-input-notification");
        } else if (!password.equals(confirmPassword)) {
            alertLabel.setText("รหัสผ่านไม่ตรงกัน");
            alertLabel.setStyle("-fx-text-fill: invalid-input-notification");
            confirmPasswordPasswordField.clear();
        } else if (accountList.canRegister(username)) {
            accountList.registerNewAccount(username, password, name);
            dataSource.writeData(accountList);
            headerLabel.setText("สมัครสำเร็จแล้ว");
            mainLabel.setText("เริ่มช็อปปิ้งกันเลย!");
            handleLoginScreenBtn();
        } else {
            alertLabel.setText("Username นี้ถูกใช้แล้ว");
            alertLabel.setStyle("-fx-text-fill: invalid-input-notification");
        }
    }

    private void clear() {
        inputUsernameTextField.clear();
        inputPasswordField.clear();
        mainLabel.setText("เข้าสู่ระบบเพื่อยืนยันตัวตน");
        mainLabel.setStyle("-fx-text-fill: sub-text");
        alertLabel.setText("สมัครง่าย ๆ ไม่กี่ขั้นตอน");
        alertLabel.setStyle("-fx-text-fill: sub-text");
        nameTextField.clear();
        usernameTextField.clear();
        passwordPasswordField.clear();
        confirmPasswordPasswordField.clear();
    }

    @FXML
    public void handleRegisterScreenBtn() {
        clear();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), containImageViewVBox);
        transition.setToX(containImageViewVBox.getLayoutX() + (root.getPrefWidth() - containImageViewVBox.getPrefWidth()));
        transition.play();
    }

    @FXML
    public void handleLoginScreenBtn() {
        clear();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), containImageViewVBox);
        transition.setToX(root.getLayoutX());
        transition.play();
    }

    @FXML
    public void clickCloseLabel() {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }
}
