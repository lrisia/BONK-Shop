package ku.cs.models.verify;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private String reporterUsername;
    private String productId;
    private String category;
    private String topic;
    private String detail;
    private String reportDate;
    private String reportTime;

    public Report(String reporterUsername, String productId, String category, String topic, String detail) {
        this(reporterUsername, productId, category, topic, detail, "", "");
        initialReportTime();
    }

    public Report(String reporterUsername, String productId,  String category, String topic, String detail, String reportDate, String reportTime) {
        this.reporterUsername = reporterUsername;
        this.productId = productId;
        this.category = category;
        this.topic = topic;
        this.detail = detail;
        this.reportDate = reportDate;
        this.reportTime = reportTime;
    }

    public void initialReportTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        reportDate = time[0];
        reportTime = time[1];
    }

    public String toCsv() {
        return reporterUsername + "," + productId + "," + category +
                "," + topic + "," + detail + "," + reportDate + ","
                + reportTime;
    }

    @Override
    public String toString() {
        String categoryTH = "";
        if (category.equals("product")) categoryTH = "สินค้า";
        else if (category.equals("review")) categoryTH = "รีวิว";
        else categoryTH = "ไม่มีข้อมูล";
        return "รายงานโดย: " + reporterUsername + " [หมวดหมู่: " + categoryTH + "/หัวข้อ: " + topic +"]\n" +
                "วันที่รายงาน: " + reportDate;
    }
}
