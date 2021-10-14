package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class PurchaseSuccessfulController {
    @FXML private ImageView logoImageView;

    public void switchToHome() throws IOException {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
