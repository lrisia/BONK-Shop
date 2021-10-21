package ku.cs.services;

import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserDataSource implements DataSource<AccountList>{
    private String directory;
    private String filename;

    public UserDataSource() {
        this("data", "userData.csv");
    }

    public UserDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        FileService.initialFileIfNotExist(directory, filename);
    }

    @Override
    public AccountList readData() {
        AccountList accountList = new AccountList();
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
                String type = data[0];
                String username = data[1];
                String password = data[2];
                String name = data[3];
                String storeName = data[4];
                int lowProductAlert = Integer.parseInt(data[5]);
                boolean banStatus = Boolean.parseBoolean(data[6]);
                int tryLoginWhenGotBanned = Integer.parseInt(data[7]);
                String loginDate = data[8];
                String loginTime = data[9];
                String ImagePath = data[10];
                accountList.addAccount(new Account(type, username, password, name, storeName, lowProductAlert, banStatus, tryLoginWhenGotBanned, loginDate, loginTime,ImagePath));
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
        return accountList;
    }

    @Override
    public void writeData(AccountList accountList) {
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(accountList.toCsv());
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
