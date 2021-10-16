package ku.cs.controllers.verify;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.Shop;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.Account;
import ku.cs.services.Effect;

import java.io.IOException;

public class ReportController {
    @FXML ImageView productImageView;
    @FXML Label productStoreNameLabel;
    @FXML Label productNameLabel;
    @FXML Label priceLabel;
    @FXML TextArea detailTextArea;
    @FXML MenuButton topicMenuButton;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();

    private Effect effect = new Effect();

    @FXML
    public void initialize() {
        productNameLabel.setText(product.getProductName());
        productStoreNameLabel.setText(product.getShopName());
        priceLabel.setText(String.format("%.2f", product.getPrice()) + " ฿");
        productImageView.setImage(new Image(product.getImagePath()));
        effect.centerImage(productImageView);
        topicMenuButton.getItems().addAll(
                new MenuItem("สินค้าไม่ตรงตามลายระเอียด/ไม่ตรงปก"),
                new MenuItem("เป็นสินค้าผิดกฏหมาย"),
                new MenuItem("มีคำหยาบคาย/ไม่เหมาะสม"));
    }

    @FXML
    public void back() {
        try {
            FXRouter.goTo("detail", new Shop(account, product, null));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main", new Shop(account, null, null));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void enterReportButton() {
        try {
            FXRouter.goTo("purchase_successful", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า purchase_successful ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
