package ku.cs.models.verify;

import ku.cs.models.shop.ProductList;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import ku.cs.services.ReportDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private String reporterUsername;
    private String productId;
    private String storeName;
    private String category;
    private String topic;
    private String detail;
    private String reportDate;
    private String reportTime;

    public Report(String reporterUsername, String productId, String category, String topic, String detail) {
        this(reporterUsername, productId, "", category, topic, detail, "", "");
        DataSource<ProductList> dataSource = new ProductDataSource();
        ProductList productList = dataSource.readData();
        storeName = productList.getStoreNameByProductId(productId);
        initialReportTime();
    }

    public Report(String reporterUsername, String productId, String storeName, String category, String topic, String detail, String reportDate, String reportTime) {
        this.reporterUsername = reporterUsername;
        this.productId = productId;
        this.category = category;
        this.topic = topic;
        this.detail = detail;
        this.reportDate = reportDate;
        this.reportTime = reportTime;
        this.storeName = storeName;
    }

    public void initialReportTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        reportDate = time[0];
        reportTime = time[1];
    }

    public String getReporterUsername() {
        return reporterUsername;
    }

    public String getCategory() {
        return category;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public String getProductId() { return productId; }

    public String getStoreName() {
        return storeName;
    }

    public String toCsv() {
        return reporterUsername + "," + productId + "," + storeName +
                "," + category + "," + topic + "," + detail + "," +
                reportDate + "," + reportTime;
    }

    @Override
    public String toString() {
        return "รายงานโดย: " + reporterUsername + " [หมวดหมู่: " + category + "/หัวข้อ: " + topic +"]\n" +
                "วันที่รายงาน: " + reportDate;
    }
}
