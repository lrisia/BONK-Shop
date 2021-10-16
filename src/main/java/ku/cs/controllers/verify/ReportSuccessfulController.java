package ku.cs.controllers.verify;

import ku.cs.models.shop.Shop;

import java.io.IOException;

public class ReportSuccessfulController {
    private Shop shop = (Shop) com.github.saacsos.FXRouter.getData();

    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
