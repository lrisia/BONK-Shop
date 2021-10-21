package ku.cs.controllers.userdata;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Shop;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class InfoPageController {
    @FXML ImageView logoImageView;

    private Shop shop = (Shop) FXRouter.getData();

    @FXML
    public void switchToHome() throws IOException {
        try {
            FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
