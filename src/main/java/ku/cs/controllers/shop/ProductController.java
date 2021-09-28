package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.App;
import ku.cs.models.shop.Item;

public class ProductController {

    @FXML private Label nameLabel;

    @FXML private Label priceLabel;

    @FXML private ImageView img;

    private Item item;

    public void setData(Item item){
        this.item = item;
        nameLabel.setText(item.getName());
        priceLabel.setText(App.CURRENCY + item.getPrice());
//        Image image = new Image(getClass().getResourceAsStream(item.getImgSrc()));
//        img.setImage(image);
    }

}
