package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import ku.cs.services.UserDataSource;
import ku.cs.strategy.CategoryProductFilterer;
import ku.cs.strategy.MyStoreProductFilterer;

import java.io.IOException;
import java.util.ArrayList;

public class MyStorePageController {

    @FXML GridPane storeGridPane;
    @FXML Label storeNameLabel;

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
        ProductList filtered =  productList.filter(new CategoryProductFilterer(account.getStoreName()));
        ArrayList<Product> products = filtered.getProductList();
        int column = 0;
        int row = 1;
        try {
            for (Product product: products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shop/my_product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                MyProductController myProductController = fxmlLoader.getController();
                myProductController.setData(product);
                if(column == 4){
                    column = 0;
                    row++;
                }
                storeGridPane.add(anchorPane,column++, row);
                GridPane.setMargin(anchorPane,new Insets(9));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToInfo(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("info",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า info ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToAddProduct(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("add_product",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add_product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
