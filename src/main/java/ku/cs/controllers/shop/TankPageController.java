package ku.cs.controllers.shop;

import javafx.fxml.FXML;

import java.io.IOException;

public class TankPageController {



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
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
