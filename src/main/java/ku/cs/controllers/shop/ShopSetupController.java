package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.UserDataSource;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class ShopSetupController {
    private Account account = (Account) com.github.saacsos.FXRouter.getData();

    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();

    @FXML private TextField storeNameTextField;
    @FXML Label alertLabel;
    @FXML
    public void switchToHome() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void startBusiness(){
        String storeName = storeNameTextField.getText();
        if(storeName.equals("")){
            alertLabel.setText("ยังไม่ได้ใส่ชื่อร้านค้า");
            alertLabel.setStyle("-fx-text-fill: #FFFFFF");
        }
        else{
            accountList.registerNewStoreByUsername(account.getUsername(),storeName);
            dataSource.writeData(accountList);
            try{
                com.github.saacsos.FXRouter.goTo("store",account);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า main ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }





}
