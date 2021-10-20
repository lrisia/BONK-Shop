package ku.cs.models.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AccountList {
    private ArrayList<Account> accountList;

    public AccountList() { accountList = new ArrayList<>(); }

    public void addAccount(Account account) { accountList.add(account); }

    public ArrayList<Account> getAllAccountExceptAdmin() {
        ArrayList<Account> allAccount = new ArrayList<>();
        for (Account account: accountList) {
            if (account.isAdmin()) continue;
            allAccount.add(account);
        }
        return allAccount;
    }

    public Account searchAccountByUsername(String username) {
        for (Account account: accountList)
            if (account.checkAccount(username)) return account;
        return null;
    }

    public Account searchAccountByShopName(String shopName){
        for (Account account: accountList)
            if(account.checkAccount(shopName)) return account;
        return null;
    }

    public Account searchAccountByLoginAccount(Account loginAccount) {
        for (Account account: accountList)
            if (account.equals(loginAccount)) return account;
        return null;
    }

    public void setNewLowProductAlertValue(Account loginAccount, int amount) {
        Account account = searchAccountByLoginAccount(loginAccount);
        account.setLowProductAlert(amount);
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

    public boolean changePasswordByUsername(String username, String newPassword) {
        Account account = searchAccountByUsername(username);
        account.changePassword(newPassword);
        return true;
    }

    public void registerNewStoreByUsername(String username,String storeName){
        Account account = searchAccountByUsername(username);
        if(account != null)
        account.registerNewStore(storeName);
    }

    public void removeAccount(Account account){
        accountList.remove(account);
    }

    public void sort(Comparator<Account> accountComparator) {
        Collections.sort(this.accountList, accountComparator);
    }

    public String toCsv() {
        String result = "";
        for (Account account: accountList) {
            result += account.toCsv() + "\n";
        }
        return result;
    }


}
