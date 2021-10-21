package ku.cs.controllers.shopSetting;

import javafx.fxml.FXML;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.services.DataSource;
import ku.cs.services.UserDataSource;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.github.saacsos.FXRouter;

public class ShopSetupController {
    @FXML private TextField storeNameTextField;
    @FXML private Label alertLabel;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private DataSource<AccountList> dataSource = new UserDataSource();
    private AccountList accountList = dataSource.readData();

    @FXML
    public void startBusiness(){
        String storeName = storeNameTextField.getText();
        if(storeName.equals("")){
            alertLabel.setText("ยังไม่ได้ใส่ชื่อร้านค้า");
            alertLabel.setStyle("-fx-text-fill: #FFFFFF");
        }
        else{
            accountList.registerNewStoreByUsername(account.getUsername(), storeName);
            dataSource.writeData(accountList);
            account = accountList.searchAccountByUsername(account.getUsername());
            try{
                com.github.saacsos.FXRouter.goTo("store", new Shop(account));
            } catch (IOException e) {
                System.err.println("ไปที่หน้า store ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
