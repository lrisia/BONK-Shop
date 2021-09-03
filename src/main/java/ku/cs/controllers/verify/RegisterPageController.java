package ku.cs.controllers.verify;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterPageController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private PasswordField confirmPasswordPasswordField;
    @FXML private Label loginLabel;
    @FXML private Label closeLabel;
    @FXML private Label alertLabel;

    @FXML //ปุ่มปิด
    public void clickCloseLabel(Event event) {
        Stage stage = (Stage) closeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML //ปุ่มไปหน้า login
    private void switchToLoginPage() {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    //Mouse event บนคำว่า "เข้าสู่ระบบ" ―――――――――――――――――――――――――
    @FXML //เม้าส์อยู่บน "เข้าสู่ระบบ"
    private void mouseEnteredLoginLabel(MouseEvent mouseEvent) {
        loginLabel.setStyle("-fx-text-fill: #7597fd"); //เปลี่ยนสี Label
        System.out.println("Mouse entered login label");
    }

    @FXML //เม้าส์ไม่อยู่บน "เข้าสู่ระบบ"
    private void mouseExitedLoginLabel(MouseEvent mouseEvent) {
        loginLabel.setStyle("-fx-text-fill: #575757"); //เปลี่ยนสีกลับ
        System.out.println("Mouse exited login label");
    }
    //――――――――――――――――――――――――――――――――――――――――――

    //ตรวจสอบความถูกฅ้องของรหัสที่สมัคร ――――――――――――――――――――――――――
    @FXML //เช็คว่ารหัสที่กรอกสองช่องตรงกันไหม
    private void checkMatchConfirmPassword() {
        if (passwordPasswordField.getText().equals(confirmPasswordPasswordField.getText())) {
            System.out.println("• รหัสผ่านตรงกัน");
            registerNewAccount();
        } else {
            alertLabel.setText("รหัสผ่านไม่ตรงกัน โปรดตรวจสอบความถูกต้อง");
            alertLabel.setStyle("-fx-text-fill: #f61e1e");
            System.out.println("x รหัสผ่านไม่ตรงกัน");
            confirmPasswordPasswordField.clear();
            passwordPasswordField.clear();
        }
    }

    // ต้องมีตัวเลขกับตัวหนังสือ ยาวอย่างน้อย 6 ตัว เพื่อความปลอดภัย
    //―――――――――――――――――――――――――――――――――――――――――――

    //บันทึกข้อมูลลงไฟล์ ―――――――――――――――――――――――――――――――――――
    @FXML
    private void registerNewAccount() {
        try {
            FileWriter fileWriter = new FileWriter("data/userCSV.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(nameTextField.getText() + "," + usernameTextField.getText() + "," + passwordPasswordField.getText());
            printWriter.flush();
            printWriter.close();

//            loginPageController.setNotificationLabelLoginPage("สมัครสมาชิกสำเร็จ");
            System.out.println("New account has been recorded!");

            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    //―――――――――――――――――――――――――――――――――――――――――――
}
