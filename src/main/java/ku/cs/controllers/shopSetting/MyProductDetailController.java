package ku.cs.controllers.shopSetting;

import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;

import java.io.IOException;

public class MyProductDetailController {
    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();

    @FXML
    public void backBtn() {
        try {
            FXRouter.goTo("store", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
