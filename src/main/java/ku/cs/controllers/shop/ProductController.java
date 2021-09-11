package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import ku.cs.models.stock.Product;

import java.util.Objects;

public class ProductController {

    @FXML
    private HBox box;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label manufacturerName;

    private String [] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void setData(Product product){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(product.getImageSrc())));

        productImage.setImage(image);

        productName.setText(product.getName());
        manufacturerName.setText(product.getManufacturer());
        box.setStyle("-fx-background-color: "+ Color.web(colors[(int)(Math.random()*colors.length)]));

    }
}
