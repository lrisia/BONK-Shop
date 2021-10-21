package ku.cs.controllers.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.ProductDataSource;
import com.github.saacsos.FXRouter;
import ku.cs.services.UserDataSource;
import ku.cs.strategy.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HomePageController {
    @FXML private ScrollPane mainScrollPane;
    @FXML private GridPane grid;
    @FXML private ComboBox sortComboBox;
    @FXML private TextField maxPriceTextField;
    @FXML private TextField minPriceTextField;
    @FXML private TextField searchTextField;
    @FXML private Label noProductLabel;
    @FXML private Label headerLabel;
    @FXML private Pane noProductPane;

    private Shop shop = (Shop) FXRouter.getData();
    protected Account account = shop.getBuyer();
    private DataSource<ProductList> productListDataSource;
    private ProductList productList;
    private DataSource<AccountList> accountListDataSource;
    private AccountList accountList;
    private Effect effect;
    private double max;
    private double min;
    private String category;
    private String search;

    public void initialize() {
        readData();
        showProduct(productList);
        initializeListener();
        handleMaxMinTextField();
    }

    private void readData() {
        productListDataSource = new ProductDataSource();
        productList = productListDataSource.readData();
        accountListDataSource = new UserDataSource();
        accountList = accountListDataSource.readData();
        effect = new Effect();
        account = accountList.searchAccountByUsername(account.getUsername());
        max = productList.getMaxPrice();
        min = 0;
        category = "All";
        search = "";
    }

    private void initializeListener() {
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

    @FXML
    public void handleMaxMinTextField() {
        maxPriceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\.]\\d{0,2})?")) {
                    maxPriceTextField.setText(oldValue);
                }
            }
        });
        minPriceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\.]\\d{0,2})?")) {
                    minPriceTextField.setText(oldValue);
                }
            }
        });
    }

    @FXML
    public void handlePriceFilter() {
        if (!maxPriceTextField.getText().equals("")) {
            try {
                double maxInput = Double.parseDouble(maxPriceTextField.getText());
                this.max = maxInput;
            } catch (NumberFormatException e) {
                System.out.println(maxPriceTextField.getText());
                System.err.println("format ผิด");
            }
        } else this.max = productList.getMaxPrice();

        if (!minPriceTextField.getText().equals("")) {
            try {
                double minInput = Double.parseDouble(minPriceTextField.getText());
                this.min = minInput;
            } catch (NumberFormatException e) {
                System.out.println(minPriceTextField.getText());
                System.err.println("format ผิด");
            }
        } else this.min = 0;
        showProduct(productList);
    }

    public ProductList priceFilter(ProductList productList, double max, double min) {
        ProductList filtered = productList.filter(new PriceProductFilterer(max, min));
        return filtered;
    }

    public ProductList categoryFilter(ProductList productList, String category) {
        ProductList filtered = productList.filter(new CategoryProductFilterer(category));
        return filtered;
    }

    public ProductList searchFilter(ProductList productList, String search) {
        ProductList filtered = productList.filter(new SearchProductFilterer(search));
        return filtered;
    }

    public void showProduct(ProductList productList) {
        clear();
        effect.fadeInPage(mainScrollPane);
        noProductLabel.setOpacity(0);
        noProductPane.setDisable(true);
        productList = searchFilter(productList, search);
        productList = priceFilter(productList, max, min);
        if (!category.equals("All"))
            productList = categoryFilter(productList, category);
        ArrayList<Product> products = productList.getProductList();
        if (products.size() == 0) {
            noProductLabel.setOpacity(0.45);
            noProductPane.setDisable(false);
        }
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
                GridPane.setMargin(anchorPane, new Insets(7));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToTank() {
        headerLabel.setText("รถถัง");
        category = "รถถัง";
        showProduct(productList);
    }

    @FXML
    public void switchToPlane() {
        headerLabel.setText("เครื่องบิน");
        category = "เครื่องบิน";
        showProduct(productList);
    }

    @FXML
    public void switchToCar() {
        headerLabel.setText("รถ");
        category = "รถ";
        showProduct(productList);
    }

    @FXML
    public void switchToWarship() {
        headerLabel.setText("เรือ");
        category = "เรือ";
        showProduct(productList);
    }

    @FXML
    public void switchToGun() {
        headerLabel.setText("ปืน");
        category = "ปืน";
        showProduct(productList);
    }

    @FXML
    public void switchToKnife() {
        headerLabel.setText("ระยะประชิด");
        category = "ระยะประชิด";
        showProduct(productList);
    }

    @FXML
    public void switchToAssault() {
        headerLabel.setText("ปืนกล");
        category = "ปืนกล";
        showProduct(productList);
    }

    @FXML
    public void refresh() {
        headerLabel.setText("สินค้าทั้งหมด");
        category = "All";
        showProduct(productList);
    }

    @FXML
    public void handleSearchIcon() {
        search = searchTextField.getText();
        showProduct(productList);
    }

    private void clear() {
        grid.getChildren().clear();
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
    public void switchToProfile(Event event) {
        try {
            if(account.isAdmin()){
                FXRouter.goTo("admin", shop);
            }
            else{
                FXRouter.goTo("profile", shop);
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToStore(Event event) {
        try {
            if (account.isSeller()) {
                FXRouter.goTo("store", shop);
            } else if (!account.isAdmin()) {
                FXRouter.goTo("shop_setup", shop);
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
