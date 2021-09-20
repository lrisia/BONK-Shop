package ku.cs.controllers.verify;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;
import java.util.SimpleTimeZone;

public class AdminController {
    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
