package ku.cs.controllers.userdata;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Shop;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class InfoPageController {
    @FXML ImageView logoImageView;
    @FXML ImageView profileImageView1;
    @FXML ImageView profileImageView2;
    @FXML ImageView profileImageView3;

    private Shop shop = (Shop) FXRouter.getData();

    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
