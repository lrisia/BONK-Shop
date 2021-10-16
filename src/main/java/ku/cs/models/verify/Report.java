package ku.cs.models.verify;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private String reporterUsername;
    private String productId;
    private String topic;
    private String detail;
    private String reportDate;
    private String reportTime;



    public void initialReportTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        reportDate = time[0];
        reportTime = time[1];
    }
}
