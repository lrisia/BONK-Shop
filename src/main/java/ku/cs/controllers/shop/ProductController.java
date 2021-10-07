package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Product;

public class ProductController {

    @FXML private Label nameLabel;

    @FXML private Label priceLabel;

    @FXML private ImageView img;

    private Product item;

    public void setData(Product item){
        this.item = item;
        nameLabel.setText(item.getProductName());
        priceLabel.setText(item.getPrice()+ "");
        Image image = new Image(item.getImagePath());
        img.setImage(image);
    }



}
