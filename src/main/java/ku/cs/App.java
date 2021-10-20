package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
//        stage.setResizable(false);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
        FXRouter.bind(this, stage, 800, 600);
        stage.setResizable(false);
        stage.getIcons().add(new Image(new FileInputStream("data/Images/app_logo.png")));
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        configRoute();
        FXRouter.goTo("login_register");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("login", packageStr+ "verify/login.fxml",600,400);
        FXRouter.when("register", packageStr+ "verify/register.fxml",600,400);
        FXRouter.when("login_register", packageStr+ "verify/login_register.fxml",600,400);
        FXRouter.when("main", packageStr+"shop/home.fxml");
        FXRouter.when("info",packageStr+"userdata/info_page.fxml",900,700);
        FXRouter.when("profile",packageStr+"userdata/profile.fxml");
        FXRouter.when("store",packageStr+"shopSetting/my_store.fxml");
        FXRouter.when("shop_setup",packageStr+"shopSetting/shop_setup.fxml");
        FXRouter.when("add_product",packageStr+"shopSetting/add_product.fxml");
        FXRouter.when("admin",packageStr+"verify/admin.fxml");
        FXRouter.when("detail",packageStr+"shop/detail.fxml");
        FXRouter.when("purchase_successful",packageStr+"shop/purchase_successful.fxml");
        FXRouter.when("specific",packageStr+"shop/specific_store.fxml");
        FXRouter.when("summary",packageStr+"shop/summary.fxml");
        FXRouter.when("report",packageStr+"verify/report.fxml");
        FXRouter.when("report_successful",packageStr+"verify/report_successful.fxml");
        FXRouter.when("my_product_detail",packageStr+"shopSetting/my_product_detail.fxml");
        FXRouter.when("order_manage",packageStr+"shopSetting/order_manage.fxml");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("ku/cs/shop/home.fxml"));
//        primaryStage.setTitle("BONK Shop");
//        primaryStage.setScene(new Scene(root, 800, 600));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        launch();
    }
}