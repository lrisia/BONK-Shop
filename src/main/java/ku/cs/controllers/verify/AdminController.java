package ku.cs.controllers.verify;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.verify.Account;
import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.AccountList;
import ku.cs.services.Effect;
import ku.cs.services.UserDataSource;

public class AdminController {
    @FXML ListView accountListView;
    @FXML ImageView userImageView;
    @FXML Label usernameLabel;
    @FXML Label nameLabel;
    @FXML Label storeNameLabel;
    @FXML Label timeLabel;
    @FXML Label saveSuccessfulLabel;
    @FXML Label tryLoginLabel;
    @FXML Label loginLabel;
    @FXML PasswordField newPasswordField;
    @FXML PasswordField confirmPasswordField;

    private UserDataSource userDataSource = new UserDataSource("data", "userData.csv");
    private AccountList accountList = userDataSource.readData();
    private Effect effect = new Effect();
    private Account account = (Account) FXRouter.getData();
    private Account selectedAccount = null;

    public void initialize() {
        userImageView.setImage(new Image(account.getImagePath()));
        showListView();
        handleSelectedListView();
    }

    private void showListView() {
        accountListView.getItems().addAll(accountList.getAllAccountExceptAdmin());
        accountListView.refresh();
    }

    private void handleSelectedListView() {
        accountListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Account>() {
                    @Override
                    public void changed(ObservableValue<? extends Account> observable,
                                        Account oldValue, Account newValue) {
                        showSelectedAccount(newValue);
                    }
                });
    }

    private void showSelectedAccount(Account account) {
        usernameLabel.setText(account.getUsername());
        nameLabel.setText(account.getName());
        storeNameLabel.setText(account.getRole());
        timeLabel.setText(account.getTime());
        selectedAccount = account;
        if (account.gotBanned()) {
            tryLoginLabel.setText("Try Login");
            loginLabel.setText(String.valueOf(account.getTryLoginWhenGotBanned()));
        } else {
            tryLoginLabel.setText("");
            loginLabel.setText("");
        }
    }

    public void ban() {
        if (selectedAccount != null) {
            selectedAccount.switchBanStatus();
            userDataSource.writeData(accountList);
            accountListView.refresh();
        }
    }

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

    public void changPassword() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String username = account.getUsername();
        if (newPassword.equals("") || confirmPassword.equals("")) {
            saveSuccessfulLabel.setText("ยังกรอกไม่ครบ");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            clear();
        } else if (!newPassword.equals(confirmPassword)) {
            saveSuccessfulLabel.setText("รหัสผ่านไม่ตรงกัน");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            clear();
        } else {
            accountList.changePasswordByUsername(username, newPassword);
            saveSuccessfulLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            clear();
        }
    }

    public void clear() {
        newPasswordField.clear();
        confirmPasswordField.clear();
    }
}
