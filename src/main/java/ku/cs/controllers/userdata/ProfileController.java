package ku.cs.controllers.userdata;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.FileService;
import ku.cs.services.UserDataSource;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import static ku.cs.controllers.verify.LoginPageController.getUsername;

public class ProfileController {
    public static File fileSelected;
    @FXML ImageView profileImageView;
    @FXML ImageView logoImageView;
    @FXML TextField showUserNameTextField;
    @FXML TextField showNameTextField;
    @FXML TextField showShopNameTextField;
    @FXML PasswordField oldPasswordPasswordField;
    @FXML PasswordField newPasswordPasswordField;
    @FXML PasswordField confirmPasswordPasswordField;
    @FXML Label saveSuccessfulLabel;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    private Effect effect;

    @FXML
    public void initialize() {
        readData();
        profileImageView.setImage(new Image(account.getImagePath()));
        effect.centerImage(profileImageView);
        showUserNameTextField.setText(account.getUsername());
        showNameTextField.setText(account.getName());
        showShopNameTextField.setText(account.getStoreName());
    }

    private void readData() {
        dataSource = new UserDataSource();
        accountList = dataSource.readData();
        account = accountList.searchAccountByUsername(account.getUsername());
        effect = new Effect();
    }

    @FXML
    public void switchToHome() throws IOException {
        try {
            FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void switchToLoginPage() {
        try {
            FXRouter.goTo("login_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUploadButton(ActionEvent event) {
        String directory = "data/Images/profiles";
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG GIF", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if(file != null){
            Image image = FileService.handleUploadPicture(file, account, directory);
            profileImageView.setImage(image);
            effect.centerImage(profileImageView);
            dataSource.writeData(accountList);
        }
    }

    public void changeUserPassword() {
        String oldPassword = oldPasswordPasswordField.getText();
        String newPassword = newPasswordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();
        String username = account.getUsername();
        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            saveSuccessfulLabel.setText("ยังกรอกไม่ครบ");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            clear();
        } else if (!accountList.canLogin(username, oldPassword)) {
            saveSuccessfulLabel.setText("รหัสผ่านไม่ถูกต้อง");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            clear();
        } else if (!newPassword.equals(confirmPassword)) {
            saveSuccessfulLabel.setText("รหัสผ่านไม่ตรงกัน");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            clear();
        } else {
            saveSuccessfulLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
            accountList.changePasswordByUsername(username, newPassword);
            dataSource.writeData(accountList);
            clear();
        }
    }

    public void clear() {
        oldPasswordPasswordField.clear();
        newPasswordPasswordField.clear();
        confirmPasswordPasswordField.clear();
    }
}
