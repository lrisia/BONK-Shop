package ku.cs.controllers.verify;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ku.cs.models.verify.AccountList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.SimpleTimeZone;

public class AdminController {
    @FXML TableView userTableView;
    @FXML ImageView userImageView;
    @FXML Label nameLabel;
    @FXML Label storeNameLabel;
    @FXML Button saveBtn;
    @FXML Button banBtn;
    @FXML TextField passwordTextField;

    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
