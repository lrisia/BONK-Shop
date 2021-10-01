package ku.cs.controllers.shop;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ku.cs.models.verify.Account;

import java.io.IOException;

public class StorePageController {
    private Account account = (Account) com.github.saacsos.FXRouter.getData();
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
            com.github.saacsos.FXRouter.goTo("info",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า info ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToSetup(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("setup");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า setup ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToAddProduct(Event event) throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("add_product",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add_product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToProfile(Event event) throws IOException {
        try {
            if(account.isAdmin()){
                com.github.saacsos.FXRouter.goTo("admin", account);
            }
            else{
                com.github.saacsos.FXRouter.goTo("profile");
            }
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
        }
    }


}
