package ku.cs.controllers.shopSetting;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.ReviewList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.ReportList;
import ku.cs.services.*;

import java.io.File;
import java.io.IOException;

public class MyProductDetailController {
    @FXML private TextField storeNameTextField;
    @FXML private TextField productNameTextField;
    @FXML private TextField priceTextField;
    @FXML private Spinner<Integer> productPieceSpinner;
    @FXML private ImageView productImageView;
    @FXML private ImageView trashIconImageView;
    @FXML private TextArea detailTextArea;
    @FXML private Label notificationLabel;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();
    private DataSource<ProductList> productListDataSource;
    private ProductList productList;
    private DataSource<ReportList> reportDataSource;
    private ReportList reportList;
    private DataSource<ReviewList> reviewListDataSource;
    private ReviewList reviewList;
    private Effect effect;
    private int newAmount;

    @FXML
    public void initialize() {
        readData();
        initializeUI();
        handlePriceTextFieldListener();
        handleProductPieceSpinnerListener();
    }

    private void readData() {
        productListDataSource = new ProductDataSource();
        productList = productListDataSource.readData();
        product = productList.searchProductById(product.getId());
        reportDataSource = new ReportDataSource();
        reportList = reportDataSource.readData();
        reviewListDataSource = new ReviewDataSource();
        reviewList = reviewListDataSource.readData();
        effect = new Effect();
        newAmount = product.getStock();
    }

    private void initializeUI() {
        storeNameTextField.setText(product.getShopName());
        productNameTextField.setText(product.getProductName());
        priceTextField.setText(String.format("%.2f", product.getPrice()));
        detailTextArea.setText(product.getDetail());
        trashIconImageView.setImage(new Image(getClass().getResource("/images/remove_product.png").toExternalForm()));
        productImageView.setImage(new Image(product.getImagePath()));
        effect.centerImage(productImageView);
    }

    private void handlePriceTextFieldListener() {
        priceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\.]\\d{0,2})?")) {
                    priceTextField.setText(oldValue);
                }
            }
        });
    }

    private void handleProductPieceSpinnerListener() {
        SpinnerValueFactory<Integer> valueFactoryAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000);
        valueFactoryAmount.setValue(product.getStock());
        productPieceSpinner.setValueFactory(valueFactoryAmount);
        productPieceSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                newAmount = newValue;
            }
        });
    }

    @FXML
    public void handleUploadButton(ActionEvent event) {
        String directory = "data/Images/products";
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG GIF", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            Image image = FileService.handleUploadPicture(file, product, directory);
            productListDataSource.writeData(productList);
            productImageView.setImage(image);
            effect.centerImage(productImageView);
        }
    }

    @FXML
    public void handleUpdateProductInfo() {
        if (productNameTextField.getText().equals("")) {
            notificationLabel.setText("ยังไม่ได้กรอกชื่อสินค้า");
        } else if (priceTextField.getText().equals("")) {
            notificationLabel.setText("ยังไม่ได้กรอกราคา");
        } else if (detailTextArea.getText().equals("")) {
            notificationLabel.setText("ยังไม่ได้กรอกรายละเอียด");
        } else {
            try {
                String productName = productNameTextField.getText();
                String price = priceTextField.getText();
                String detail = detailTextArea.getText();
                productList.editProductInformation(product, productName, price, newAmount, detail);
                productListDataSource.writeData(productList);
                FXRouter.goTo("store", new Shop(account));
            } catch (IOException e) {
                System.err.println("ไปที่หน้า store ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } effect.fadeOutLabelEffect(notificationLabel, 3);
    }

    @FXML
    public void handleRemoveProduct() {
        productList.removeProduct(product);
        reviewList.removeAllReviewByProductId(product.getId());
        productListDataSource.writeData(productList);
        reviewListDataSource.writeData(reviewList);
        try {
            FXRouter.goTo("store", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void backBtn() {
        try {
            FXRouter.goTo("store", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void backToHome() {
        try {
            FXRouter.goTo("main", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
