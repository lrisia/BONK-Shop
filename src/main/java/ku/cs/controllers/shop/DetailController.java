package ku.cs.controllers.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import ku.cs.services.UserDataSource;

import java.io.IOException;

public class DetailController {
    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private TextArea detailTextArea;
    @FXML private Label productTotalLabel;
    @FXML private Label priceTotalLabel;
    @FXML private Spinner<Integer> productPieceSpinner;
    @FXML private Label productStoreNameLabel;
    @FXML private Button buyGoodBtn;

    private Product item = (Product) com.github.saacsos.FXRouter.getData(); //เก็บข้อมูลจากหน้าสินค้ามาไว้ในนี้ด้วย


    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();
    private DataSource<ProductList> productListDataSource = new ProductDataSource();
    private ProductList productList = productListDataSource.readData();

    private int currentPiece;


    public void initialize(){
        if (item.getStock() == 0) {
            buyGoodBtn.setStyle("-fx-background-color: #9e9e9e");
            buyGoodBtn.setText("Out of stock");
        }
        productImageView.setImage(new Image(item.getImagePath()));
        productImageView.resize(245,280);
        productStoreNameLabel.setText(item.getShopName());
        productNameLabel.setText(item.getProductName());
        priceLabel.setText(item.getPrice()+" ฿");
        detailTextArea.setWrapText(true);
        detailTextArea.setText(item.getDetail());
        productTotalLabel.setText("There are "+item.getStock()+" items left.");
        SpinnerValueFactory<Integer> valueFactoryPiece = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,item.getStock());
        valueFactoryPiece.setValue(0);
        productPieceSpinner.setValueFactory(valueFactoryPiece);
        currentPiece = productPieceSpinner.getValue();
        priceTotalLabel.setText(("Totals price "+currentPiece*item.getPrice()+" ฿"));
        productPieceSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentPiece = productPieceSpinner.getValue();
                priceTotalLabel.setText(("Totals price "+String.format("%.2f",currentPiece*item.getPrice())+" ฿"));
            }
        });
    }

    @FXML
    private void mouseEnterStoreNameLabel(MouseEvent mouseEvent) {
        productStoreNameLabel.setStyle("-fx-text-fill: #7597fd"); //เปลี่ยนสี Label
    }

    @FXML
    private void mouseExitedStoreNameLabel(MouseEvent mouseEvent) {
        productStoreNameLabel.setStyle("-fx-text-fill: #000000"); //เปลี่ยนสีกลับ
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

    @FXML
    public void switchToSpecific() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("specific");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า specific ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToReport() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("Report");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void buyGoods() {
        if (!(item.getStock() == 0)) {
            try {
                DataSource<ProductList> dataSource = new ProductDataSource();
                ProductList productList = dataSource.readData();
                Product product = productList.searchProductById(item.getId());
                product.setStock(product.getStock() - currentPiece);
                dataSource.writeData(productList);
                com.github.saacsos.FXRouter.goTo("purchase_successful");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า purchase_successful ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }


}
