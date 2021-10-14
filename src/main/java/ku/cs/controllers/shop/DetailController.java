package ku.cs.controllers.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Order;
import javafx.scene.input.MouseEvent;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.services.Effect;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class DetailController {
    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private Label maxProductLabel;
    @FXML private TextArea detailTextArea;
    @FXML private Label productTotalLabel;
    @FXML private Label priceTotalLabel;
    @FXML private Spinner<Integer> productPieceSpinner;
    @FXML private Label productStoreNameLabel;
    @FXML private Button buyGoodBtn;

    private Shop shop = (Shop) FXRouter.getData();
    private Product item = shop.getProduct(); //เก็บข้อมูลจากหน้าสินค้ามาไว้ในนี้ด้วย
    private Account account = shop.getBuyer();

    Effect effect = new Effect();
    private int currentPiece;

    public void initialize(){
        int startingAmount = 1;
        if (item.getStock() == 0) {
            buyGoodBtn.setStyle("-fx-background-color: #9e9e9e");
            buyGoodBtn.setText("สินค้าหมด");
            startingAmount = 0;
        }
        productImageView.setImage(new Image(item.getImagePath()));
        productImageView.resize(245,280);
        productStoreNameLabel.setText(item.getShopName());
        productNameLabel.setText(item.getProductName());
        priceLabel.setText(item.getPrice()+" ฿");
        detailTextArea.setWrapText(true);
        detailTextArea.setText(item.getDetail());
        productTotalLabel.setText("มีสินค้า "+item.getStock()+" ชิ้น");
        SpinnerValueFactory<Integer> valueFactoryPiece = new SpinnerValueFactory.IntegerSpinnerValueFactory(startingAmount, item.getStock()+1);
        valueFactoryPiece.setValue(0);
        productPieceSpinner.setValueFactory(valueFactoryPiece);
        currentPiece = productPieceSpinner.getValue();
        priceTotalLabel.setText(("ทั้งหมดราคา "+currentPiece*item.getPrice()+" บาท"));
        productPieceSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if (t1 == item.getStock()+1) {
                    effect.fadeOutLabelEffect(maxProductLabel, 3);
                    valueFactoryPiece.setValue(item.getStock());
                    productPieceSpinner.setValueFactory(valueFactoryPiece);
                }
                currentPiece = productPieceSpinner.getValue();
                priceTotalLabel.setText(("ทั้งหมดราคา "+String.format("%.2f",currentPiece*item.getPrice())+" บาท"));
            }
        });
    }

//    @FXML
//    public void changeProductAmount(InputMethodEvent inputMethodEvent) {
//        if (productPieceSpinner.getValue() > item.getStock()) {
//            buyGoodBtn.setStyle("-fx-background-color: #9e9e9e");
//            buyGoodBtn.setText("สินค้าหมด");
//        }
//    }

    @FXML
    private void mouseEnterStoreNameLabel(MouseEvent mouseEvent) {
        productStoreNameLabel.setStyle("-fx-text-fill: #7597fd"); //เปลี่ยนสี Label
    }

    @FXML
    private void mouseExitedStoreNameLabel(MouseEvent mouseEvent) {
        productStoreNameLabel.setStyle("-fx-text-fill: #000000"); //เปลี่ยนสีกลับ
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToSpecific() {
        try {
            com.github.saacsos.FXRouter.goTo("specific");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า specific ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToReport() {
        try {
            FXRouter.goTo("Report");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void buyGoods() {
        if ((!(item.getStock() == 0)) && currentPiece <= item.getStock()) {
            try {
                String price = String.format("%.2f", currentPiece*item.getPrice());
                Order order = new Order(account.getName(), item.getShopName(), item, currentPiece, price);
                shop.setOrder(order);
                FXRouter.goTo("summary", shop);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า summary ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
    }


}
