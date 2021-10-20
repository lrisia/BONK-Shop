package ku.cs.controllers.verify;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Product;
import ku.cs.models.shop.Shop;
import com.github.saacsos.FXRouter;
import ku.cs.models.verify.Account;
import ku.cs.models.verify.Report;
import ku.cs.models.verify.ReportList;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import ku.cs.services.ReportDataSource;

import java.io.IOException;

public class ReportController {
    @FXML ImageView productImageView;
    @FXML Label productStoreNameLabel;
    @FXML Label productNameLabel;
    @FXML Label priceLabel;
    @FXML Label notificationLabel;
    @FXML TextArea detailTextArea;
    @FXML ComboBox topicComboBox;

    private Shop shop = (Shop) FXRouter.getData();
    private Account account = shop.getBuyer();
    private Product product = shop.getProduct();

    private DataSource<ReportList> reportListDataSource;
    private ReportList reportList;

    private Effect effect = new Effect();

    @FXML
    public void initialize() {
        productNameLabel.setText(product.getProductName());
        productStoreNameLabel.setText(product.getShopName());
        priceLabel.setText(String.format("%.2f", product.getPrice()) + " ฿");
        productImageView.setImage(new Image(product.getImagePath()));
        effect.centerImage(productImageView);
        topicComboBox.getItems().addAll(
                "สินค้าไม่ตรงตามรายละเอียด",
                "เป็นสินค้าผิดกฏหมาย",
                "มีคำหยาบคาย/ไม่เหมาะสม",
                "ละเมิดลิขสิทธ์",
                "ไม่ส่งของตามจริง",
                "สินค้าอันตราย/มีความรุนแรง");
        reportListDataSource = new ReportDataSource();
        reportList = reportListDataSource.readData();
    }

    @FXML
    public void back() {
        try {
            FXRouter.goTo("detail", new Shop(account, product, null));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main", new Shop(account, null, null));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void enterReportButton() {
        if (detailTextArea.getText().equals(""))
            notificationLabel.setText("โปรดใส่รายละเอียด");
        else if (topicComboBox.getValue() == null)
            notificationLabel.setText("โปรดเลือกหัวข้อ");
        else {
            String topic = topicComboBox.getValue().toString();
            String detail = detailTextArea.getText();
            reportList.addNewReport(account.getUsername(), product.getId(), "สินค้า", topic, detail);
            reportListDataSource.writeData(reportList);
            try {
                FXRouter.goTo("report_successful", new Shop(account));
            } catch (IOException e) {
                System.err.println("ไปที่หน้า report_successful ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } effect.fadeOutLabelEffect(notificationLabel, 3);
    }
}
