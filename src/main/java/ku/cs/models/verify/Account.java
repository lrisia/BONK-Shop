package ku.cs.models.verify;

import ku.cs.models.shop.ProductList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String name;
    private String username;
    private String password;
    private boolean banStatus;
    private String loginDate;
    private String loginTime;

    private ProductList productList;

    public Account(String username, String password, String name) {
        this(username, password, name, false, "", "");
        initialLoginTime();
    }

    public Account(String username, String password, String name, boolean banStatus, String loginDate, String loginTime) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.banStatus = banStatus;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
    }

    public void initialLoginTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        loginDate = time[0];
        loginTime = time[1];
    }

    public boolean canLogin(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password))
            return true;
        return false;
    }

    public String getLoginDataTime() {
        return loginDate + " " + loginTime;
    }

    public boolean checkAccount(String username) {
        if (this.username.equals(username)) return true;
        return false;
    }

    public String toCsv() {
        return "Account," + username + "," + password + "," + name + ","
                + banStatus + "," + loginDate + "," + loginTime;
    }

    // todo: What user can do?



}
