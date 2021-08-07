package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import javafx.stage.StageStyle;

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
        stage.initStyle(StageStyle.UNDECORATED);
        configRoute();
        FXRouter.goTo("login");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("login", packageStr+ "login_page.fxml",600,400);
        FXRouter.when("register", packageStr+ "register_page.fxml",600,400);
        FXRouter.when("main", packageStr+"main_page.fxml");
        FXRouter.when("basket",packageStr+"basket.fxml");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}