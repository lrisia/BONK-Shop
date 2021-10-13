package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import ku.cs.models.verify.Account;

import java.io.IOException;

public class PurchaseSuccessfulController {
    @FXML private ImageView logoImageView;

    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
