package ku.cs.models.verify;

import ku.cs.models.shop.Review;

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

    public void removeReportFromProductId(String productId) {
        ArrayList<Report> reportLists = new ArrayList<>();
        for (Report report: reportList) {
            if (report.getProductId().equals(productId)) reportLists.add(report);
        } for (Report report: reportLists) {
            if (report.getProductId().equals(productId)) reportList.remove(report);
        }
    }

    public void deleteReport(Report report) {
        Report target = null;
        for (Report thisReport: reportList) {
            if (thisReport.equals(report)) target = thisReport;
        } reportList.remove(target);
    }

    public String toCsv() {
        String result = "";
        for (Report report: reportList) {
            result += report.toCsv() + "\n";
        } return result;
    }
}
