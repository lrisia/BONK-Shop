package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Product;

import java.io.IOException;

public class ReportController {
    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private TextArea reportTextArea;
    @FXML private Button reportBtn;

    private Product item = (Product) com.github.saacsos.FXRouter.getData();

    public void initialize(){
        productImageView.setImage(new Image(item.getImagePath()));
        productImageView.resize(245,280);
        productNameLabel.setText(item.getProductName());
        priceLabel.setText(item.getPrice()+"");
//        reportTextArea.setWrapText(true);
//        reportTextArea.setText(item.getDetail());
    }

    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
