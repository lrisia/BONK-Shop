package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import ku.cs.services.UserDataSource;
import ku.cs.strategy.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SpecificStoreController {
    @FXML private GridPane storeGrid;
    @FXML private Label storeNameLabel;

    private Account account = (Account) com.github.saacsos.FXRouter.getData();


    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();
    private DataSource<ProductList> productListDataSource = new ProductDataSource();
    private ProductList productList = productListDataSource.readData();


    public void initialize() {
        showProduct(productList);
        storeNameLabel.setText(account.getStoreName());
    }

    public void showProduct(ProductList productList) {
        account = accountList.searchAccountByUsername(account.getUsername());
        ArrayList<Product> products = productList.getProductList();
        int column = 0;
        int row = 1;
        try {
            for (Product product: products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shop/product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductController productController = fxmlLoader.getController();
                productController.setData(product);
                if(column == 4){
                    column = 0;
                    row++;
                }
                storeGrid.add(anchorPane,column++, row);
                GridPane.setMargin(anchorPane,new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToTank() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Tank"));
        showProduct(filtered);
    }

    @FXML
    public void switchToPlane() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Plane"));
        showProduct(filtered);

    }

    @FXML
    public void switchToCar() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Car"));
        showProduct(filtered);
    }

    @FXML
    public void switchToWarship() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Warship"));
        showProduct(filtered);
    }

    @FXML
    public void switchToGun() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Gun"));
        showProduct(filtered);
    }

    @FXML
    public void switchToKnife() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Knife"));
        showProduct(filtered);
    }

    @FXML
    public void switchToAssault() {
        ProductList filtered = productList.filter(new CategoryProductFilterer("Assault rifle"));
        showProduct(filtered);
    }

    @FXML
    public void switchToInfo(Event event) {
        try {
            com.github.saacsos.FXRouter.goTo("info");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า info ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToProfile(Event event) {
        try {
            if(account.isAdmin()){
                com.github.saacsos.FXRouter.goTo("admin", account);
            }
            else{
                com.github.saacsos.FXRouter.goTo("profile", account);
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToStore(Event event) {
        try {
            if(account.isSeller()){
                com.github.saacsos.FXRouter.goTo("store", account);
            }
            else{
                com.github.saacsos.FXRouter.goTo("shop_setup", account);
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
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
    public void refresh() {
        showProduct(productList);
    }

    void clear() {
        storeGrid.getChildren().clear();
    }
}
