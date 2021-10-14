package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.Shop;

import java.io.IOException;

public class ProductController extends HomePageController{
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private ImageView img;

    private Shop shop;
    private Product item;

    @Override
    public void initialize() {
        return;
    }

    public void setData(Product item){
        this.item = item;
        nameLabel.setText(item.getProductName());
        priceLabel.setText(item.getPrice()+ " ฿");
        Image image = new Image(item.getImagePath());
        img.setImage(image);
    }

    @FXML
    public void switchToDetail() {
        shop = new Shop(account, item, null);
        try {
            com.github.saacsos.FXRouter.goTo("detail", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
