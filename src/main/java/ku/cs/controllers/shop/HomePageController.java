package ku.cs.controllers.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import ku.cs.services.UserDataSource;
import ku.cs.strategy.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HomePageController {
    @FXML private ImageView logoImageView;
    @FXML private TextField searchTextField;
    @FXML private ImageView searchImageView;
    @FXML private ImageView informationImageView;
    @FXML private ImageView userImageView;
    @FXML private ImageView myShopImageView;
    @FXML private ImageView tankIconImageView;
    @FXML private ImageView planeIconImageView;
    @FXML private ImageView carIconImageView;
    @FXML private ImageView boatIconImageView;
    @FXML private ImageView gunIconImageView;
    @FXML private ImageView knifeIconImageView;
    @FXML private ImageView assaultIconImageView;
    @FXML private HBox cardLayout;
    @FXML private ScrollPane scroll;
    @FXML private GridPane grid;
    @FXML private ComboBox sortComboBox;

    private Account account = (Account) com.github.saacsos.FXRouter.getData();

    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();
    private DataSource<ProductList> productListDataSource = new ProductDataSource();
    private ProductList productList = productListDataSource.readData();


    public void initialize() {
        showProduct(productList);
        sortComboBox.getItems().addAll("ล่าสุด", "เก่าสุด", "ราคาสูงสุด", "ราคาต่ำสุด");
        sortComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue,
                                String oldString, String newString) {
                if (newString.equals("ล่าสุด")) {
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if(o1.getTime().compareTo(o2.getTime()) > 0) return -1;
                            if(o1.getTime().compareTo(o2.getTime()) < 0) return 1;
                            return 0;
                        }
                    });
                    showProduct(productList);
                } else if (newString.equals("เก่าสุด")) {
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if(o1.getTime().compareTo(o2.getTime()) > 0) return 1;
                            if(o1.getTime().compareTo(o2.getTime()) < 0) return -1;
                            return 0;
                        }
                    });
                    showProduct(productList);
                } else if (newString.equals("ราคาสูงสุด")) {
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if(o1.getPrice() > o2.getPrice()) return -1;
                            if(o1.getPrice() < o2.getPrice()) return 1;
                            return 0;
                        }
                    });
                    showProduct(productList);
                } else if (newString.equals("ราคาต่ำสุด")) {
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if(o1.getPrice() > o2.getPrice()) return 1;
                            if(o1.getPrice() < o2.getPrice()) return -1;
                            return 0;
                        }
                    });
                    showProduct(productList);
                }
            }
        });
    }

    public void showProduct(ProductList productList) {
        account = accountList.searchAccountByUsername(account.getUsername());
        ArrayList <Product> products = productList.getProductList();
        int column = 0;
        int row = 1;
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
                grid.add(anchorPane,column++, row);
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
    public void refresh() {
        showProduct(productList);
    }

    void clear() {
        grid.getChildren().clear();
    }

}
