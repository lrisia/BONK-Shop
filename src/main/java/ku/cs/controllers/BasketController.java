package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class BasketController
{
    @FXML private Label price01Label;
    @FXML private Label price02Label;
    @FXML private Label amount01Label;
    @FXML private Label amount02Label;
    @FXML private CheckBox store01Checkbox;
    @FXML private CheckBox product01Checkbox;
    @FXML private CheckBox product02Checkbox;
    @FXML private CheckBox allProductCheckbox;
    @FXML private SplitMenuButton option01SplitMenuBtn;
    @FXML private SplitMenuButton option02SplitMenuBtn;
    @FXML private Spinner piece01Spinner;
    @FXML private Spinner piece02Spinner;
    @FXML private Button delete01Btn;
    @FXML private Button delete02Btn;
    @FXML private Button purchaseBtn;

    @FXML
    public void switchToMainPage() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
