package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import ku.cs.models.stock.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController{
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




    // private double xOffset = 0;
    //  private double yOffset = 0;

    // Stage stage = (Stage) logoImageView.getScene().getWindow();

//    @FXML
//    public void initialize() {
//        // stage.close();
//        // FXRouter.bind(this, stage, 800, 600);
//        // stage.setResizable(true);
//        // stage.initStyle(StageStyle.DECORATED);
//    }

    //ความพยายามอันล้มเหลวที่จะทำให้ลากหน้าจอได้――――――――――――――――――――
    @FXML
    public void setOnMousePressed(MouseEvent event) {
        // xOffset = event.getSceneX();
        // yOffset = event.getSceneY();
    }

    @FXML
    public void setOnMouseDragged(MouseEvent event) {
        // stage.setX(event.getSceneX() - xOffset);
        // stage.setY(event.getSceneX() - yOffset);
    }
    //――――――――――――――――――――――――――――――――――――――――――


    @FXML
    public void switchToTank() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("tank");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า tank ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void switchToPlane() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("plane");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า plane ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToCar() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("car");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า car ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToBoat() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("boat");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า boat ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToGun() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("gun");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า gun ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToKnife() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("knife");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า knife ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToAssault() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("assault");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า assault ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void switchToInfo(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("info");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า info ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToProfile(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("setup");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า setup ไม่ได้");
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








//    @FXML
//    public void switchToTank() throws IOException {
//        try {
//            com.github.saacsos.FXRouter.goTo("tank");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า register ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
//    }





//    private List<Product> recentlyAdded;
//
//    @FXML
//    public void initialize(URL location, ResourceBundle resourceBundle) {
//        recentlyAdded = new ArrayList<>(recentlyAdded());
//        try {
//            for(int i = 0; i< recentlyAdded.size(); i++){
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("ku/cs/shop/product.fxml"));
//                HBox cardBox = fxmlLoader.load();
//                ProductController productController = fxmlLoader.getController();
//                productController.setData(recentlyAdded.get(i));
//                cardLayout.getChildren().add(cardBox);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private List<Product> recentlyAdded(){
//        List<Product> ls = new ArrayList<>();
//        Product product = new Product();
//        product.setName("P990-Razer");
//        product.setImageSrc("images/icon_category/p990_product.png");
//        product.setManufacturer("Military");
//        ls.add(product);
//        return ls;
//    }
}
