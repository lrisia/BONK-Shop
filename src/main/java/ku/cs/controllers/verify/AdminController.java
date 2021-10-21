package ku.cs.controllers.verify;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.ProductList;
import ku.cs.models.shop.Shop;
import ku.cs.models.verify.Account;
import java.io.IOException;
import java.util.Comparator;

import com.github.saacsos.FXRouter;
import ku.cs.models.verify.AccountList;
import ku.cs.models.verify.Report;
import ku.cs.models.verify.ReportList;
import ku.cs.services.*;

public class AdminController {
    @FXML ListView showDataListView;
    @FXML ImageView userImageView;
    @FXML PasswordField newPasswordField;
    @FXML PasswordField confirmPasswordField;
    @FXML Button banBtn;
    @FXML Button accountManageBtn;
    @FXML Button reportManageBtn;
    @FXML Label saveSuccessfulLabel;
    // ส่วนของข้อมูล account
    @FXML Pane accountPane;
    @FXML Label usernameLabel;
    @FXML Label nameLabel;
    @FXML Label storeNameLabel;
    @FXML Label timeLabel;
    @FXML Label tryLoginLabel;
    @FXML Label loginLabel;
    // ส่วนของข้อมูล report
    @FXML Pane reportPane;
    @FXML Label reportUsernameLabel;
    @FXML Label reportCategoryLabel;
    @FXML Label reportTopicLabel;
    @FXML Label ownUsernameLabel;
    @FXML TextArea detailTextArea;
    @FXML Button deleteReportBtn;

    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    private DataSource<ReportList> reportListDataSource;
    private ReportList reportList;
    private DataSource<ProductList> productDataSource;
    private ProductList productList;
    private Effect effect;
    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Account selectedAccount = null;
    private Report selectedReport = null;
    private ChangeListener myListener = null;
    private ChangeListener<Account> accountListener = null;
    private ChangeListener<Report> reportListener = null;

    public void initialize() {
        readData();
        accountList.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (o1.getTime().isAfter(o2.getTime())) return -1;
                if (o1.getTime().isBefore(o2.getTime())) return 1;
                return 0;
            }
        });
        accountListener = new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observable,
                                Account oldValue, Account newValue) {
                showSelectedAccount(newValue);
            }
        };
        reportListener = new ChangeListener<Report>() {
            @Override
            public void changed(ObservableValue<? extends Report> observableValue,
                                Report oldValue, Report newValue) {
                showSelectedReport(newValue);
            }
        };
        myListener = accountListener;
        showDataListView.getSelectionModel().selectedItemProperty().addListener(myListener);
        reportPane.setOpacity(0);
        showAccountInListView();
        detailTextArea.setDisable(true);
        deleteReportBtn.setDisable(true);
    }

    private void readData() {
        dataSource = new UserDataSource();
        accountList = dataSource.readData();
        reportListDataSource = new ReportDataSource();
        reportList = reportListDataSource.readData();
        productDataSource = new ProductDataSource();
        productList = productDataSource.readData();
        effect = new Effect();
    }

    @FXML
    public void handleAccountMenu() {
        showAccountInListView();
        reportPane.setOpacity(0);
        accountPane.setOpacity(1);
        effect.fadeInPage(accountPane);
        if (myListener.equals(reportListener)) {
            showDataListView.getSelectionModel().selectedItemProperty().removeListener(reportListener);
            showDataListView.getSelectionModel().selectedItemProperty().addListener(accountListener);
            myListener = accountListener;
        }
        banBtn.setDisable(false);
        banBtn.setVisible(true);
        deleteReportBtn.setDisable(true);
        deleteReportBtn.setOpacity(0);
        accountManageBtn.setOpacity(1);
        reportManageBtn.setOpacity(0.75);
        detailTextArea.setDisable(true);
    }

    @FXML
    public void handleReportMenu() {
        showReportInListView();
        accountPane.setOpacity(0);
        reportPane.setOpacity(1);
        effect.fadeInPage(reportPane);
        if (myListener.equals(accountListener)) {
            showDataListView.getSelectionModel().selectedItemProperty().removeListener(accountListener);
            showDataListView.getSelectionModel().selectedItemProperty().addListener(reportListener);
            myListener = reportListener;
        }
        banBtn.setDisable(true);
        banBtn.setVisible(false);
        deleteReportBtn.setDisable(false);
        deleteReportBtn.setOpacity(1);
        reportManageBtn.setOpacity(1);
        accountManageBtn.setOpacity(0.75);
        detailTextArea.setDisable(false);
    }

    private void showAccountInListView() {
        clearData();
        userImageView.setImage(new Image(getClass().getResource("/images/profileDefault.png").toExternalForm()));
        effect.centerImage(userImageView);
        showDataListView.getItems().addAll(accountList.getAllAccountExceptAdmin());
        showDataListView.refresh();
    }

    private void showReportInListView() {
        clearData();
        userImageView.setImage(new Image(getClass().getResource("/images/product_default_white.png").toExternalForm()));
        effect.centerImage(userImageView);
        showDataListView.getItems().addAll(reportList.getAllReportLog());
        showDataListView.refresh();
    }

    private void showSelectedAccount(Account account) {
        if (account != null) {
            usernameLabel.setText(account.getUsername());
            nameLabel.setText(account.getName());
            storeNameLabel.setText(account.getStoreName());
            timeLabel.setText(account.getLoginDateTime());
            selectedAccount = account;
            userImageView.setImage(new Image(account.getImagePath()));
            effect.centerImage(userImageView);
            if (account.gotBanned()) {
                banBtn.setText("ปลดแบน");
                tryLoginLabel.setText("จำนวนครั้งที่เข้าสู่ระบบระหว่างถูกแบน :");
                loginLabel.setText(String.valueOf(account.getTryLoginWhenGotBanned()));
            } else {
                banBtn.setText("แบน");
                tryLoginLabel.setText("");
                loginLabel.setText("");
            }
        }
    }

    private void showSelectedReport(Report report) {
        if (report != null) {
            reportUsernameLabel.setText(report.getReporterUsername());
            reportCategoryLabel.setText(report.getCategory());
            reportTopicLabel.setText(report.getTopic());
            ownUsernameLabel.setText(report.getStoreName());
            detailTextArea.setText(report.getDetail());
            selectedReport = report;
            String imagePath = productList.getProductImagePathByProductId(report.getProductId());
            if (imagePath == null) userImageView.setImage(new Image(getClass().getResource("/images/product_default_white.png").toExternalForm()));
            else userImageView.setImage(new Image(imagePath));
            effect.centerImage(userImageView);
        }
    }

    public void deleteReportBtn() {
        if (selectedReport != null) {
            reportList.deleteReport(selectedReport);
            reportListDataSource.writeData(reportList);
            showReportInListView();
        }
    }

    public void ban() {
        if (selectedAccount != null) {
            selectedAccount.switchBanStatus();
            dataSource.writeData(accountList);
            showDataListView.refresh();
            showSelectedAccount(selectedAccount);
        }
    }

    public void switchToHome() throws IOException {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void switchToLoginPage() {
        try {
            FXRouter.goTo("login_register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login_register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private void backToHome() {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void changPassword() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String username = account.getUsername();
        if (newPassword.equals("") || confirmPassword.equals("")) {
            saveSuccessfulLabel.setText("ยังกรอกไม่ครบ");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
        } else if (!newPassword.equals(confirmPassword)) {
            saveSuccessfulLabel.setText("รหัสผ่านไม่ตรงกัน");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
        } else {
            accountList.changePasswordByUsername(username, newPassword);
            saveSuccessfulLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
            effect.fadeOutLabelEffect(saveSuccessfulLabel, 5);
            dataSource.writeData(accountList);
        } clearPasswordField();
    }

    public void clearPasswordField() {
        newPasswordField.clear();
        confirmPasswordField.clear();
    }

    public void clearData() {
        showDataListView.getItems().clear();
        usernameLabel.setText("-");
        nameLabel.setText("-");
        storeNameLabel.setText("-");
        timeLabel.setText("-");
        tryLoginLabel.setText("");
        loginLabel.setText("");
        reportUsernameLabel.setText("-");
        reportCategoryLabel.setText("-");
        reportTopicLabel.setText("-");
        ownUsernameLabel.setText("-");
        detailTextArea.clear();
        effect.fadeInPage(showDataListView);
        effect.fadeInPage(accountManageBtn);
        effect.fadeInPage(reportManageBtn);
    }
}
