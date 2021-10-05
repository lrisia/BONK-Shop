package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ku.cs.models.shop.Item;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.UserDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
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
    @FXML private Button adminBtn;
    @FXML private ScrollPane scroll;
    @FXML private GridPane grid;

    private Account account = (Account) com.github.saacsos.FXRouter.getData();
    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();

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
    public void switchToStore(Event event) throws IOException {
        try {
            if(account.isSeller()){
                com.github.saacsos.FXRouter.goTo("store",account);
            }
            else{
                com.github.saacsos.FXRouter.goTo("shop_setup",account);
            }

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
//    public void showProduct (ProductList product) {
//        recentlyAdded = new ArrayList<>(recentlyAdded());//get data ,get text
//        try {
//            for(int i = 0; i< recentlyAdded.size(); i++){
//
//                HBox cardBox = new
//                ProductController productController = fxmlLoader.getController();
//                productController.setData(recentlyAdded.get(i));
//                cardLayout.getChildren().add(cardBox);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private ImageView imageView;
//    private BorderPane mainPane;
//    private Label textLabel;
//    private HBox hBox;
//
//    public void start(Stage homePageController) throws Exception {
//
////        this.imageView = new ImageView("images/icon_category/p990_product.png");
////        imageView.setFitWidth(240);
////        imageView.setPreserveRatio(true);
//
////        this.textLabel = new Label("Hello world!");
////        textLabel.setWrapText(true);
//
//        HBox hBox = new HBox(8);
//        ImageView image1 = new ImageView("images/icon_category/p990_product.png");
//        HBox.setHgrow(image1, Priority.ALWAYS);
//
//
//        cardLayout.getChildren().addAll(hBox,image1, textLabel);
//
//
//        homePageController.setScene(new Scene(mainPane));
//        homePageController.show()


    private List<Item> items = new ArrayList<>();

    private List<Item> getData(){
        List<Item> items =  new ArrayList<>();
        Item item;
        for(int i = 0; i <= 20; i++){
            item = new Item();
            item.setName("Tank");
            item.setPrice(2000000);
//            item.setImgSrc("images/product/rc_tank.jpg");
            item.setColor("6A7324");
            items.add(item);
        }
        return items;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account = accountList.searchAccountByUsername(account.getUsername());

        items.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i=0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shop/product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProductController productController = fxmlLoader.getController();
                productController.setData(items.get(i));
                if(column == 3){
                    column = 0;
                    row++;
                }
                grid.add(anchorPane,column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
