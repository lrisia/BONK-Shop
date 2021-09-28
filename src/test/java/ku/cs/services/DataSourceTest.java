package ku.cs.services;

import ku.cs.models.verify.Account;
import ku.cs.models.verify.AccountList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceTest {

    @Test
    void testWriteDataSource() {
        String directory = "unit-test-userData";
        String filename = "userData01.csv";
        DataSource<AccountList> dataSource;
        dataSource = new UserDataSource(directory, filename);

        AccountList accountList = new AccountList();
        accountList.addAccount(new Account("Irisia", "1234", "Ton"));
        accountList.addAccount(new Account("Mellon", "4321", "Mell"));
        dataSource.writeData(accountList);

        File file = new File(directory);
        assertTrue(file.exists());
        file = new File(directory + File.separator + filename);
        assertTrue(file.isFile());
    }
}