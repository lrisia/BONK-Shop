package ku.cs.controllers.verify;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import java.io.IOException;
import java.util.Comparator;

import com.github.saacsos.FXRouter;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
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

    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();
    private Effect effect = new Effect();
    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Account selectedAccount = null;

    public void initialize() {
        accountList.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (o1.getTime().isAfter(o2.getTime())) return -1;
                if (o1.getTime().isBefore(o2.getTime())) return 1;
                return 0;
            }
        });
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
        storeNameLabel.setText(account.getStoreName());
        timeLabel.setText(account.getLoginDateTime());
        selectedAccount = account;
        userImageView.setImage(new Image(account.getImagePath()));
        if (account.gotBanned()) {
            tryLoginLabel.setText("จำนวนครั้งที่เข้าสู่ระบบระหว่างถูกแบน :");
            loginLabel.setText(String.valueOf(account.getTryLoginWhenGotBanned()));
        } else {
            tryLoginLabel.setText("");
            loginLabel.setText("");
        }
    }

    public void ban() {
        if (selectedAccount != null) {
            selectedAccount.switchBanStatus();
            dataSource.writeData(accountList);
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

    @FXML
    private void backToHome() {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
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
            dataSource.writeData(accountList);
        }
    }

    public void clear() {
        newPasswordField.clear();
        confirmPasswordField.clear();
    }
}
