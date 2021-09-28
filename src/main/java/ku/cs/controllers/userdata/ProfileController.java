package ku.cs.controllers.userdata;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.UserDataSource;

import java.io.File;
import java.io.IOException;

import static ku.cs.controllers.verify.LoginPageController.getUsername;

public class ProfileController {
    public static File fileSelected;
    private UserDataSource userDataSource;
    @FXML Label userNameLabel;
    @FXML ImageView profileImageView;
    @FXML ImageView logoImageView;
    @FXML Button upLoadBtn;
    @FXML Button finishBtn;
    @FXML TextField storeNameTextField;


    @FXML
    public void initialize() {
        userDataSource = new UserDataSource();
        AccountList accountList = userDataSource.readData();
        Account account = accountList.searchAccountByUsername(getUsername);
        profileImageView.setImage(new Image(account.getImagePath()));
    }

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

    @FXML
    public void upLoadPic(){
        FileChooser fileChooser = new FileChooser();
        fileSelected = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(new  FileChooser.ExtensionFilter("image", ".jpg", ".png"));
        if(fileSelected != null){
            Image image = new Image(fileSelected.toURI().toString());
            profileImageView.setImage(image);
            userDataSource = new UserDataSource();
            AccountList accountList = userDataSource.readData();
            Account account = accountList.searchAccountByUsername(getUsername);
            accountList.removeAccount(account);
            account.setImagePath();
            accountList.addAccount(account);
            userDataSource.writeData(accountList);
        }
    }



}
