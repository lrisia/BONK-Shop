package ku.cs.controllers.shopSetting;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.*;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.OrderDataSource;
import ku.cs.services.ProductDataSource;

import java.io.IOException;

public class OrderManageController {
    @FXML private Label productNameLabel;
    @FXML private Label productAmountLabel;
    @FXML private Label priceLabel;
    @FXML private Label sendProductStatusLabel;
    @FXML private Label notificationLabel;
    @FXML private Button manageNewOrderMenuBtn;
    @FXML private Button manageOldOrderMenuBtn;
    @FXML private Button acceptBtn;
    @FXML private Button trackingGenerateBtn;
    @FXML private TextField trackingNumberTextField;
    @FXML private TextField trackingNumberForAlreadySendTextField;
    @FXML private ImageView logoImageView;
    @FXML private ImageView productImageView;
    @FXML private ListView newOrderListView;

    @FXML private Shop shop = (Shop) FXRouter.getData();
    private Account account;
    private DataSource<ProductList> productListDataSource;
    private ProductList productList;
    private DataSource<OrderList> orderListDataSource;
    private OrderList orderList;
    private Order order;
    private Effect effect;

    @FXML
    public void initialize() {
        readData();
        logoImageView.setImage(new Image(getClass().getResource("/images/logo_white.png").toExternalForm()));
        productImageView.setImage(new Image(getClass().getResource("/images/product_default.png").toExternalForm()));
        handleListViewListener();
        addNewOrderInListView(orderList);
    }

    private void readData() {
        productListDataSource = new ProductDataSource();
        productList = productListDataSource.readData();
        orderListDataSource = new OrderDataSource();
        orderList = orderListDataSource.readData();
        account = shop.getBuyer();
        effect = new Effect();
    }

    private void handleListViewListener() {
        newOrderListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue,
                                Order oldValue, Order newValue) {
                handleShowData(newValue);
                order = newValue;
            }
        });
    }

    private void handleShowData(Order order) {
        trackingNumberTextField.clear();
        if (order == null) return;
        Product product = productList.searchProductById(order.getProductId());
        productImageView.setImage(new Image(product.getImagePath()));
        productNameLabel.setText(product.getProductName());
        productAmountLabel.setText("" + order.getAmount());
        priceLabel.setText(order.getPrice() + " ฿");
        String status = "ยังไม่ส่ง";
        if (!order.getTrackingNumber().equals(status)) status = "ส่งแล้ว";
        sendProductStatusLabel.setText(status);
        trackingNumberForAlreadySendTextField.setText(order.getTrackingNumber());
        effect.centerImage(productImageView);
    }

    private void addNewOrderInListView(OrderList orderList) {
        clear();
        newOrderListView.getItems().addAll(orderList.getUnSendOrderList(account.getStoreName()));
    }

    private void addAlreadySendInListView(OrderList orderList) {
        clear();
        newOrderListView.getItems().addAll(orderList.getSendOrderList(account.getStoreName()));
    }

    @FXML
    public void handleGenerateTrackingNumber() {
        if (order != null)
            trackingNumberTextField.setText(order.generateTrackingNumber());
    }

    @FXML
    public void handleEnterTrackingNumber() {
        if (trackingNumberTextField.getText().equals("")) {
            notificationLabel.setText("ยังไม่ได้ใส่ tracking number");
            effect.fadeOutLabelEffect(notificationLabel, 3);
        } else {
            String trackingNumber = trackingNumberTextField.getText();
            orderList.addTrackingNumber(order, trackingNumber);
            orderListDataSource.writeData(orderList);
            newOrderListView.getItems().clear();
            addNewOrderInListView(orderList);
        }
    }

    private void clear() {
        newOrderListView.getItems().clear();
        newOrderListView.refresh();
        productImageView.setImage(new Image(getClass().getResource("/images/product_default.png").toExternalForm()));
        effect.centerImage(productImageView);
        productNameLabel.setText("-");
        productAmountLabel.setText("-");
        priceLabel.setText("-");
        sendProductStatusLabel.setText("-");
        trackingNumberForAlreadySendTextField.clear();
    }

    @FXML
    public void handleManageAlreadySend() {
        addAlreadySendInListView(orderList);
        manageOldOrderMenuBtn.setOpacity(1);
        manageNewOrderMenuBtn.setOpacity(0.75);
        trackingNumberForAlreadySendTextField.setOpacity(1);
        trackingNumberForAlreadySendTextField.setDisable(false);
        acceptBtn.setDisable(true);
        trackingGenerateBtn.setDisable(true);
    }

    @FXML
    public void handleManageNewOrder() {
        addNewOrderInListView(orderList);
        manageNewOrderMenuBtn.setOpacity(1);
        manageOldOrderMenuBtn.setOpacity(0.75);
        trackingNumberForAlreadySendTextField.setOpacity(0);
        trackingNumberForAlreadySendTextField.setDisable(true);
        acceptBtn.setDisable(false);
        trackingGenerateBtn.setDisable(false);
    }

    @FXML
    public void handleLogoImageView() {
        try {
            FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleBackButton() {
        try {
            FXRouter.goTo("store", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
