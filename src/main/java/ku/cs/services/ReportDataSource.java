package ku.cs.services;

import ku.cs.models.verify.Report;
import ku.cs.models.verify.ReportList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReportDataSource implements DataSource<ReportList>{
    private String directory;
    private String filename;

    public ReportDataSource() {
        this("data", "reportData.csv");
    }

    public ReportDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        FileService.initialFileIfNotExist(directory, filename);
    }

    @Override
    public ReportList readData() {
        ReportList reportList = new ReportList();
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String data[] = line.split(",");
                String reporterUsername = data[0];
                String productId = data[1];
                String storeName = data[2];
                String category = data[3];
                String topic = data[4];
                String detail = data[5];
                String reportDate = data[6];
                String reportTime = data[7];
                reportList.addReport(new Report(reporterUsername, productId, storeName, category, topic, detail, reportDate, reportTime));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reportList;
    }

    @Override
    public void writeData(ReportList reportList) {
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(reportList.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
