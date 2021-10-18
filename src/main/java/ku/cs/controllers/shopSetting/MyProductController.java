package ku.cs.controllers.shopSetting;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.Shop;
import com.github.saacsos.FXRouter;
import ku.cs.services.Effect;

import java.io.IOException;

public class MyProductController extends MyStorePageController {
    @FXML private ImageView myProductImageView;
    @FXML private Label myProductNameLabel;
    @FXML private Label myProductPriceLabel;
    @FXML private Label stockLabel;

    private Product item;
    private Effect effect;

    @Override
    public void initialize() {
        effect = new Effect();
    }

    public void setData(Product item){
        this.item = item;
        myProductNameLabel.setText(item.getProductName());
        myProductPriceLabel.setText(item.getPrice()+ " ฿");
        stockLabel.setText("มี " + item.getStock() + " ชิ้น");
        Image image = new Image(item.getImagePath());
        myProductImageView.setImage(image);
        effect.centerImage(myProductImageView);
    }

    @FXML
    public void switchToDetail() {
        try {
            FXRouter.goTo("my_product_detail", new Shop(account, item));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า my_product_detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
