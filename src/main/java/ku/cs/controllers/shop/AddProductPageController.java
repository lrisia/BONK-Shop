package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class AddProductPageController implements Initializable{
    @FXML Button upLoadPic;
    @FXML ImageView productImageView;
    @FXML TextField inputProductNameTextField;
    @FXML TextField inputProductDetailTextField;
    @FXML Label notificationLabel;
    @FXML Spinner<Double> productPriceSpinner;
    @FXML Spinner<Integer> productQuantitySpinner;
    @FXML ComboBox categoryComboBox;

    Double price;
    Integer quantity;

    private DataSource<ProductList> dataSource = new ProductDataSource();
    private ProductList productList = dataSource.readData();
    private Account account = (Account) com.github.saacsos.FXRouter.getData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Double> valueFactoryPrice = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000000);
        SpinnerValueFactory<Integer> valueFactoryQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200);
        valueFactoryPrice.setValue(1.0);
        valueFactoryQuantity.setValue(0);
        productPriceSpinner.setValueFactory(valueFactoryPrice);
        productQuantitySpinner.setValueFactory(valueFactoryQuantity);
        price = productPriceSpinner.getValue();
        quantity = productQuantitySpinner.getValue();

        categoryComboBox.getItems().add("Tanks");
        categoryComboBox.getItems().add("Plane");
        categoryComboBox.getItems().add("Cars");
        categoryComboBox.getItems().add("Warship");
        categoryComboBox.getItems().add("Guns");
        categoryComboBox.getItems().add("Knife");
        categoryComboBox.getItems().add("Assault rifle");
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
    public void switchToStore(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("store");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า store ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void add(){
        String productName = inputProductNameTextField.getText();
        String productDetail = inputProductDetailTextField.getText();
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
                productList.addProduct(account.getStoreName(),productName,price,quantity,productDetail,category);
                Comparator <Product> comparator = new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        if(o1.getTime().compareTo(o2.getTime()) > 0) return -1;
                        if(o1.getTime().compareTo(o2.getTime()) < 0) return 1;
                        return 0;
                    }
                };
                productList.sort(comparator);
                dataSource.writeData(productList);
                com.github.saacsos.FXRouter.goTo("store");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า store ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            } catch (NullPointerException e) {
                notificationLabel.setText("Please selected your product category");
                notificationLabel.setStyle("-fx-text-fill: #FFFFFF");
            }
        }
    }

    @FXML
    public void upLoadPic(){
        FileChooser fileChooser = new FileChooser();
        fileSelected = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(new  FileChooser.ExtensionFilter("image", ".jpg", ".png"));
        if(fileSelected != null){
            Image image = new Image(fileSelected.toURI().toString());
            productImageView.setImage(image);
        }
    }


}
