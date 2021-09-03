package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.github.saacsos.FXRouter;
import java.io.IOException;

public class HomePageController {
    @FXML private ImageView logoImageView;
    @FXML private TextField searchTextField;
    @FXML private ImageView searchIconImageView;
    @FXML private Rectangle product01Rect;
    @FXML private Rectangle product02Rect;
    @FXML private Rectangle product03Rect;
    @FXML private Rectangle product04Rect;
    @FXML private Rectangle product05Rect;
    @FXML private Rectangle product06Rect;
    @FXML private Rectangle product07Rect;
    @FXML private Rectangle product08Rect;

    // private double xOffset = 0;
    //  private double yOffset = 0;

    // Stage stage = (Stage) logoImageView.getScene().getWindow();

    @FXML
    public void initialize() {
        // stage.close();
        // FXRouter.bind(this, stage, 800, 600);
        // stage.setResizable(true);
        // stage.initStyle(StageStyle.DECORATED);
    }

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
    public void switchToBasket() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("basket");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
