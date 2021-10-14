package ku.cs.controllers.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;
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

    private int quantity;
    private double currentPrice;
    private int currentQuantity;

    private DataSource<ProductList> dataSource = new ProductDataSource();
    private ProductList productList = dataSource.readData();
    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactoryQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200);
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

        quantity = productQuantitySpinner.getValue();

        categoryComboBox.getItems().add("Tank");
        categoryComboBox.getItems().add("Plane");
        categoryComboBox.getItems().add("Car");
        categoryComboBox.getItems().add("Warship");
        categoryComboBox.getItems().add("Gun");
        categoryComboBox.getItems().add("Knife");
        categoryComboBox.getItems().add("Assault rifle");
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
    public void switchToStore(Event event) {
        try {
            FXRouter.goTo("store", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void add() {
        String productName = inputProductNameTextField.getText();
        String productDetail = inputProductDetailTextArea.getText();
        if (productName.equals("")) {
            notificationLabel.setText("Please enter your product name");
            notificationLabel.setStyle("-fx-text-fill: #FFFFFF");
        } else if(productDetail.equals("")) {
            notificationLabel.setText("Please enter your product detail");
            notificationLabel.setStyle("-fx-text-fill: #FFFFFF");
        } else if(fileSelected == null) {
            notificationLabel.setText("Please upload your product picture");
            notificationLabel.setStyle("-fx-text-fill: #FFFFFF");
        } else {
            try {
                String category = categoryComboBox.getValue().toString();
                double price = Double.parseDouble(productPriceTextField.getText());
                productList.addProduct(account.getStoreName(),productName,price,quantity,productDetail,category);
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
                notificationLabel.setText("Please selected your product category");
                notificationLabel.setStyle("-fx-text-fill: #FFFFFF");
            } catch (NumberFormatException e) {
                notificationLabel.setText("Price format incorrect");
                notificationLabel.setStyle("-fx-text-fill: #FFFFFF");
            }
        }
    }

    @FXML
    public void upLoadPic() {
        FileChooser fileChooser = new FileChooser();
        fileSelected = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(new  FileChooser.ExtensionFilter("image", ".jpg", ".png"));
        if(fileSelected != null){
            Image image = new Image(fileSelected.toURI().toString());
            productImageView.setImage(image);
        }
    }


}
