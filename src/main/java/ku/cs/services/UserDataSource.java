package ku.cs.services;

import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;

import java.io.*;

public class UserDataSource implements DataSource<AccountList>{
    private String directory;
    private String filename;

    public UserDataSource() {
        this("data", "userData.csv");
    }

    public UserDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        InitialFileIfNotExist.initialFileIfNotExist(directory, filename);
    }

    @Override
    public AccountList readData() {
        AccountList accountList = new AccountList();
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
                String type = data[0];
                String username = data[1];
                String password = data[2];
                String name = data[3];
                String storeName = data[4];
                boolean banStatus = Boolean.parseBoolean(data[5]);
                int tryLoginWhenGotBanned = Integer.parseInt(data[6]);
                String loginDate = data[7];
                String loginTime = data[8];
                String ImagePath = data[9];
                accountList.addAccount(new Account(type, username, password, name, storeName, banStatus, tryLoginWhenGotBanned, loginDate, loginTime,ImagePath));

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
            fileWriter = new FileWriter(file);
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
