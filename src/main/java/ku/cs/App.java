package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.github.saacsos.FXRouter;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("login_page"), 600, 400);
//        stage.setResizable(false);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
        FXRouter.bind(this, stage, 600, 400);
        configRoute();
        FXRouter.goTo("login");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("login", packageStr+ "login_page.fxml");
        FXRouter.when("register", packageStr+ "register_page.fxml");
        FXRouter.when("main", packageStr+"main_page.fxml", 800, 600);
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