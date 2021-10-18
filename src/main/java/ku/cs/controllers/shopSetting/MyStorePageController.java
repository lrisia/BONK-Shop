package ku.cs.controllers.shopSetting;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import com.github.saacsos.FXRouter;
import ku.cs.services.UserDataSource;
import ku.cs.strategy.MyStoreProductFilterer;

import java.io.IOException;
import java.util.ArrayList;

public class MyStorePageController {
    @FXML GridPane storeGridPane;
    @FXML Label storeNameLabel;
    @FXML Pane noProductPane;

    private Shop shop = (Shop) FXRouter.getData();
    protected Account account = shop.getBuyer();

    private DataSource<ProductList> productListDataSource = new ProductDataSource();
    private ProductList productList = productListDataSource.readData();
    private DataSource<AccountList> accountListDataSource = new UserDataSource();
    private AccountList accountList = accountListDataSource.readData();

    public void initialize() {
        account = accountList.searchAccountByUsername(account.getUsername());
        showProduct(productList);
        storeNameLabel.setText(account.getStoreName());
    }

    public void showProduct(ProductList productList) {
        ProductList filtered =  productList.filter(new MyStoreProductFilterer(account.getStoreName()));
        ArrayList<Product> products = filtered.getProductList();
        noProductPane.setOpacity(0);
        noProductPane.setDisable(true);
        if (products.size() == 0) {
            noProductPane.setOpacity(0.45);
            noProductPane.setDisable(false);
        }
        int column = 0;
        int row = 1;
        try {
            for (Product product: products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shopSetting/my_product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                MyProductController myProductController = fxmlLoader.getController();
                myProductController.setData(product);
                if(column == 4){
                    column = 0;
                    row++;
                }
                storeGridPane.add(anchorPane,column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToInfo(Event event) {
        try {
            FXRouter.goTo("info", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า info ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToAddProduct(Event event) {
        try {
            FXRouter.goTo("add_product", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add_product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
