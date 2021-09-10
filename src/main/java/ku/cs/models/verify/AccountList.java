package ku.cs.models.verify;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accountList;

    public AccountList() { accountList = new ArrayList<>(); }

    public void addAccount(Account account) { accountList.add(account); }

    public Account searchAccountByUsername(String username) {
        for (Account account: accountList)
            if (account.checkAccount(username)) return account;
        return null;
    }

    public boolean canLogin(String username, String password) {
        Account account = searchAccountByUsername(username);
        if (account != null && account.canLogin(username, password)) return true;
        return false;
    }

    public boolean canRegister(String username) {
        Account account = searchAccountByUsername(username);
        if (account == null) return true;
        return false;
    }

    public void registerNewAccount(String username, String password, String name) {
        addAccount(new Account(username, password, name));
    }

//    public boolean registerNewAccount(String username, String password, String name) {
//        if ()
//    }

    public String toCsv() {
        String result = "";
        for (Account account: accountList) {
            result += account.toCsv() + "\n";
        }
        return result;
    }
}
