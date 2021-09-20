package ku.cs.controllers.verify;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ku.cs.models.verify.Account;
import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.AccountList;
import ku.cs.services.UserDataSource;

public class AdminController {
    @FXML ListView accountListView;
    @FXML ImageView userImageView;
    @FXML Label usernameLabel;
    @FXML Label nameLabel;
    @FXML Label storeNameLabel;
    @FXML Label timeLabel;
    @FXML Label saveSuccessfulLabel;
    @FXML Button banBtn;
    @FXML TextField passwordTextField;

    private UserDataSource userDataSource = new UserDataSource("data", "userData.csv");
    private AccountList accountList = userDataSource.readData();
    private Account account = (Account) FXRouter.getData();

    public void initialize() {
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
    }

    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void changPassword() {
        String newPassword = passwordTextField.getText();
        if ((!newPassword.equals("")) && accountList.changePasswordByUsername(account.getUsername(), newPassword)) {
            userDataSource.writeData(accountList);
            saveSuccessfulLabel.setText("save successful!");
            passwordTextField.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), saveSuccessfulLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        } else {
            saveSuccessfulLabel.setText("something incorrectly, please try again");
            passwordTextField.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), saveSuccessfulLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
    }

}
