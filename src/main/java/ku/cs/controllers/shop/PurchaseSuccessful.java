package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.image.ImageView;

public class PurchaseSuccessful {
    @FXML ImageView logoImageView;

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
