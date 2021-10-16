package ku.cs.models.verify;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reportList;

    public ReportList() {
        this.reportList = new ArrayList<>();
    }

    public void addNewReport(String reporterUsername, String productId, String category, String topic, String detail) {
        reportList.add(new Report(reporterUsername, productId, category, topic, detail));
    }

    public void addReport(Report report) {
        reportList.add(report);
    }

    public ArrayList<Report> getAllReportLog() {
        return reportList;
    }

    public String toCsv() {
        String result = "";
        for (Report report: reportList) {
            result += report.toCsv() + "\n";
        } return result;
    }
}
