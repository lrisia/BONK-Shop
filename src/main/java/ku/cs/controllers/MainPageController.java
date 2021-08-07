package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class MainPageController {
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
