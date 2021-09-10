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
        initialFileIfNotExist();
    }

    public void initialFileIfNotExist() {
        File file = new File(directory); // set directory path
        if (!file.exists()) file.mkdir(); // if file not exist will make new directory
        file = new File(directory + File.separator + filename); // set file path
        if (!file.exists()) { // if file not exist in path
            try {
                file.createNewFile(); // make new file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                if (type.equals("Account")) {
                    String username = data[1];
                    String password = data[2];
                    String name = data[3];
                    boolean banStatus = Boolean.parseBoolean(data[4]);
                    String loginDate = data[5];
                    String loginTime = data[6];
                    accountList.addAccount(new Account(username, password, name, banStatus, loginDate, loginTime));
                }
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
