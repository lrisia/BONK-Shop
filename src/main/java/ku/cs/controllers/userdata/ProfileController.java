package ku.cs.controllers.userdata;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.UserDataSource;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class ProfileController {
    @FXML Label userNameLabel;
    @FXML Label reportLabel;
    @FXML ImageView profileImageView;
    @FXML ImageView logoImageView;
    @FXML Button upLoadBtn;
    @FXML PasswordField newPasswordField;

    private UserDataSource userDataSource = new UserDataSource("data", "userData.csv");
    private AccountList accountList = userDataSource.readData();
    Account account = (Account) FXRouter.getData();

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
    public void changePassword() {
        String newPassword = newPasswordField.getText();
        if (newPassword.equals("")) {
            reportLabel.setText("รหัสผ่านไม่ถูกต้อง");
            newPasswordField.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), reportLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        } else if (newPassword.equals(account.getPassword())) {
            reportLabel.setText("รหัสผ่านเหมือนเดิม");
            newPasswordField.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), reportLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        } else {
            accountList.changePasswordByUsername(account.getUsername(), newPassword);
            userDataSource.writeData(accountList);
        }
    }
}
