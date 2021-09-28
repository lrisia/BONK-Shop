package ku.cs.controllers.userdata;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.UserDataSource;

import java.io.IOException;

public class ProfileController {
    @FXML Label userNameLabel;
    @FXML Label reportLabel;
    @FXML ImageView profileImageView;
    @FXML ImageView logoImageView;
    @FXML Button upLoadBtn;
    @FXML PasswordField newPasswordTextField;

    private UserDataSource userDataSource = new UserDataSource("data", "userData.csv");
    private AccountList accountList = userDataSource.readData();
    private Account account = (Account) com.github.saacsos.FXRouter.getData();

    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void switchToLoginPage() {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void changeUserPassword() {
        String newPassword = newPasswordTextField.getText();
        if ((!newPassword.equals("")) && accountList.changePasswordByUsername(account.getUsername(), newPassword)) {
            userDataSource.writeData(accountList);
            reportLabel.setText("save successful!");
            newPasswordTextField.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), reportLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        } else {
            reportLabel.setText("something incorrectly, please try again");
            newPasswordTextField.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), reportLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
    }
}
