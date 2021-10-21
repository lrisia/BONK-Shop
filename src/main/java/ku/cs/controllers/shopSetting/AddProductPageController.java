package ku.cs.controllers.shopSetting;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.ProductDataSource;
import java.io.IOException;
import java.util.Comparator;
import com.github.saacsos.FXRouter;

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class AddProductPageController{
    @FXML Button upLoadPic;
    @FXML ImageView productImageView;
    @FXML TextField inputProductNameTextField;
    @FXML TextField productPriceTextField;
    @FXML TextArea inputProductDetailTextArea;
    @FXML Label notificationLabel;
    @FXML Spinner<Integer> productQuantitySpinner;
    @FXML ComboBox categoryComboBox;

    private DataSource<ProductList> dataSource;
    private ProductList productList;
    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Effect effect;
    private int quantity;
    private double currentPrice;
    private int currentQuantity;

    public void initialize() {
        readData();
        initializeListener();
        initializeUI();
        quantity = productQuantitySpinner.getValue();
    }

    private void readData() {
        dataSource = new ProductDataSource();
        productList = dataSource.readData();
        effect = new Effect();
    }

    private void initializeListener() {
        SpinnerValueFactory<Integer> valueFactoryQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5000);
        valueFactoryQuantity.setValue(0);
        inputProductDetailTextArea.setWrapText(true);
        productQuantitySpinner.setValueFactory(valueFactoryQuantity);
        currentQuantity = productQuantitySpinner.getValue();
        productQuantitySpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentQuantity = productQuantitySpinner.getValue();
                quantity = productQuantitySpinner.getValue();
            }
        });
        productPriceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\.]\\d{0,2})?")) {
                    productPriceTextField.setText(oldValue);
                }
            }
        });
    }

    private void initializeUI() {
        categoryComboBox.getItems().add("รถถัง");
        categoryComboBox.getItems().add("เครื่องบิน");
        categoryComboBox.getItems().add("รถ");
        categoryComboBox.getItems().add("เรือ");
        categoryComboBox.getItems().add("ปืน");
        categoryComboBox.getItems().add("ระยะประชิด");
        categoryComboBox.getItems().add("ปืนกล");
    }

    @FXML
    public void add() {
        String productName = inputProductNameTextField.getText();
        String productDetail = inputProductDetailTextArea.getText();
        if (productName.equals("")) {
            notificationLabel.setText("โปรดกรอกชื่อสินค้า");
            notificationLabel.setStyle("-fx-text-fill: light-header-text");
        } else if(productDetail.equals("")) {
            notificationLabel.setText("โปรดกรอกรายละเอียด");
            notificationLabel.setStyle("-fx-text-fill: light-header-text");
        } else if(fileSelected == null) {
            notificationLabel.setText("โปรดเลือกรูปภาพ");
            notificationLabel.setStyle("-fx-text-fill: light-header-text");
        } else {
            try {
                String category = categoryComboBox.getValue().toString();
                double price = Double.parseDouble(productPriceTextField.getText());
                productList.addProduct(account.getStoreName(), productName, price, quantity, productDetail, category);
                Comparator<Product> comparator = new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        if(o1.getTime().compareTo(o2.getTime()) > 0) return -1;
                        if(o1.getTime().compareTo(o2.getTime()) < 0) return 1;
                        return 0;
                    }
                };
                productList.sort(comparator);
                dataSource.writeData(productList);
                FXRouter.goTo("store", shop);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า store ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            } catch (NullPointerException e) {
                notificationLabel.setText("โปรดเลือกหมวดหมู่ของสินค้า");
                notificationLabel.setStyle("-fx-text-fill: light-header-text");
            } catch (NumberFormatException e) {
                notificationLabel.setText("รูปแบบราคาไม่ถูกต้อง");
                notificationLabel.setStyle("-fx-text-fill: light-header-text");
            }
        } effect.fadeOutLabelEffect(notificationLabel, 3);
    }

    @FXML
    public void handleUploadButton() {
        FileChooser fileChooser = new FileChooser();
        fileSelected = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(new  FileChooser.ExtensionFilter("image", ".jpg", ".png"));
        if(fileSelected != null){
            Image image = new Image(fileSelected.toURI().toString());
            productImageView.setImage(image);
            effect.centerImage(productImageView);
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

    @FXML
    public void switchToStore() {
        try {
            FXRouter.goTo("store", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
