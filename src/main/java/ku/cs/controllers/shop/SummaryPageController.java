package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import ku.cs.models.shop.Order;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.Account;
import javafx.scene.image.ImageView;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;

import java.io.IOException;

public class SummaryPageController {
    @FXML Label productStoreNameLabel;
    @FXML Label productNameLabel;
    @FXML Label priceLabel;
    @FXML Label buyerNameLabel;
    @FXML Label amountLabel;
    @FXML Label summaryPriceLabel;
    @FXML ImageView productImageView;
    @FXML TextArea detailTextArea;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();
    private Order order = shop.getOrder();

    private DataSource<ProductList> dataSource;
    private ProductList productList;

    public void initialize() {
        productNameLabel.setText(product.getProductName());
        productStoreNameLabel.setText(product.getShopName());
        priceLabel.setText("" + product.getPrice() + " ฿");
        buyerNameLabel.setText(account.getName());
        amountLabel.setText("" + order.getAmount());
        summaryPriceLabel.setText("" + order.getPrice() + " ฿");
        productImageView.setImage(new Image(product.getImagePath()));
        detailTextArea.setText(product.getDetail());

        dataSource = new ProductDataSource();
        productList = dataSource.readData();
    }

    public void acceptPurchase() {
        productList.purchaseProduct(product.getId(), order.getAmount());
        dataSource.writeData(productList);
        try {
            System.out.println(order);
            FXRouter.goTo("purchase_successful");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า purchase_successful ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

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
}
