package ku.cs.controllers.userdata;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InfoPageController {
    @FXML ImageView logoImageView;
    @FXML ImageView profileImageView1;
    @FXML ImageView profileImageView2;
    @FXML ImageView profileImageView3;
    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
