package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import ku.cs.models.shop.*;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.Account;
import javafx.scene.image.ImageView;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.OrderDataSource;
import ku.cs.services.ProductDataSource;

import java.io.IOException;

public class SummaryPageController {
    @FXML Label productStoreNameLabel;
    @FXML Label productNameLabel;
    @FXML Label priceLabel;
    @FXML Label buyerNameLabel;
    @FXML Label amountLabel;
    @FXML Label summaryPriceLabel;
    @FXML Label categoryLabel;
    @FXML ImageView productImageView;
    @FXML TextArea detailTextArea;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();
    private Order order = shop.getOrder();
    private Effect effect;
    private DataSource<ProductList> dataSource;
    private ProductList productList;
    private DataSource<OrderList> orderListDataSource;
    private OrderList orderList;

    public void initialize() {
        readData();
        initializeUI();
    }

    private void readData() {
        effect = new Effect();
        dataSource = new ProductDataSource();
        productList = dataSource.readData();
        orderListDataSource = new OrderDataSource();
        orderList = orderListDataSource.readData();
    }

    private void initializeUI() {
        productNameLabel.setText(product.getProductName());
        productStoreNameLabel.setText(product.getShopName());
        priceLabel.setText(String.format("%.2f", product.getPrice()) + " ฿");
        buyerNameLabel.setText(account.getName());
        amountLabel.setText("" + order.getAmount());
        summaryPriceLabel.setText(String.format("%.2f", order.getPrice()) + " ฿");
        categoryLabel.setText(product.getCategory());
        detailTextArea.setText(product.getDetail());
        productImageView.setImage(new Image(product.getImagePath()));
        effect.centerImage(productImageView);
    }

    @FXML
    public void acceptPurchase() {
        productList.purchaseProduct(product.getId(), order.getAmount());
        dataSource.writeData(productList);
        orderList.addOrder(order);
        orderListDataSource.writeData(orderList);
        try {
            FXRouter.goTo("purchase_successful", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า purchase_successful ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
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
