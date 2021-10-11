package ku.cs.controllers.shop;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import ku.cs.models.shop.Product;

public class MyProductController {
    @FXML private ImageView myProductImageView;
    @FXML private Label myProductNameLabel;
    @FXML private Label myProductPriceLabel;

    private Product item;

    public void setData(Product item){
        this.item = item;
        myProductNameLabel.setText(item.getProductName());
        myProductPriceLabel.setText(item.getPrice()+ " ฿");
        Image image = new Image(item.getImagePath());
        myProductImageView.setImage(image);
    }




}
