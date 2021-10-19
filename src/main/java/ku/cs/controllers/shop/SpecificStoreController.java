package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import ku.cs.strategy.*;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.util.ArrayList;

public class SpecificStoreController {
    @FXML private GridPane storeGrid;
    @FXML private Label storeNameLabel;
    @FXML private Label noProductLabel;


    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();

    private DataSource<ProductList> productListDataSource = new ProductDataSource();
    private ProductList productList = productListDataSource.readData();


    public void initialize() {
        showProduct(productList);
        storeNameLabel.setText(product.getShopName());
    }

    public void showProduct(ProductList productList) {
        noProductLabel.setText("");
        productList = productList.filter(new MyStoreProductFilterer(product.getShopName()));
        ArrayList<Product> products = productList.getProductList();
        int column = 0;
        int row = 1;
        if (products.size() == 0) {
            noProductLabel.setText("ไม่พบสินค้า");
        }
        try {
            for (Product product: products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shop/product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductController productController = fxmlLoader.getController();
                productController.setData(product);
                if(column == 3){
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
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Tank"));
        showProduct(filtered);
    }

    @FXML
    public void switchToPlane() {
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Plane"));
        showProduct(filtered);

    }

    @FXML
    public void switchToCar() {
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Car"));
        showProduct(filtered);
    }

    @FXML
    public void switchToWarship() {
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Warship"));
        showProduct(filtered);
    }

    @FXML
    public void switchToGun() {
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Gun"));
        showProduct(filtered);
    }

    @FXML
    public void switchToKnife() {
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Knife"));
        showProduct(filtered);
    }

    @FXML
    public void switchToAssault() {
        clear();
        ProductList filtered = productList.filter(new CategoryProductFilterer("Assault rifle"));
        showProduct(filtered);
    }

    @FXML
    public void switchToInfo(Event event) {
        try {
            FXRouter.goTo("info", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า info ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToProfile(Event event) {
        try {
            if (account.isAdmin()) {
                FXRouter.goTo("admin", new Shop(account));
            } else {
                FXRouter.goTo("profile", new Shop(account));
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void refresh() {
        clear();
        showProduct(productList);
    }

    void clear() {
        storeGrid.getChildren().clear();
    }
}
