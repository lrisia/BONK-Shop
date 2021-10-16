package ku.cs.controllers.shop;

import com.github.saacsos.FXRouter;
import ku.cs.models.shop.Shop;

import java.io.IOException;

public class PurchaseSuccessfulController {
    private Shop shop = (Shop) FXRouter.getData();

    public void switchToHome() throws IOException {
        try {
            FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
