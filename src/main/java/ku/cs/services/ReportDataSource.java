package ku.cs.services;

import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import ku.cs.models.verify.Report;
import ku.cs.models.verify.ReportList;

import java.io.*;

public class ReportDataSource implements DataSource<ReportList>{
    private String directory;
    private String filename;

    public ReportDataSource() {
        this("data", "reportData.csv");
    }

    public ReportDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        InitialFileIfNotExist.initialFileIfNotExist(directory, filename);
    }

    @Override
    public ReportList readData() {
        ReportList reportList = new ReportList();
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String data[] = line.split(",");
                String reporterUsername = data[0];
                String productId = data[1];
                String category = data[2];
                String topic = data[3];
                String detail = data[4];
                String reportDate = data[5];
                String reportTime = data[6];
                reportList.addReport(new Report(reporterUsername, productId, category, topic, detail, reportDate, reportTime));
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
            fileWriter = new FileWriter(file);
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
