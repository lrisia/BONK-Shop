package ku.cs.controllers.shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ShopSetupFormPageController {
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label phoneNumberLabel;
    @FXML private ImageView ProfileImage;
    @FXML private TextField StoreNameTextField;
    @FXML private Label SYBLabel;
    @FXML private Label SNameLabel;
    @FXML private Button FinishButton;
    String storeName = StoreNameTextField.getText();
    public void haveStoreName(){
        if(storeName != null){
            SYBLabel.setText("");
            SNameLabel.setText("");
            StoreNameTextField.setText("");
            FinishButton.setText("");
        }
    }
    @FXML
    public void BackToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}