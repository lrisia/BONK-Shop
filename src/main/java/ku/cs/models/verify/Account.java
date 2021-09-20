package ku.cs.models.verify;

import ku.cs.models.shop.ProductList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String role;
    private String name;
    private String username;
    private String password;
    private boolean banStatus;
    private String loginDate;
    private String loginTime;

    private ProductList productList;

    public Account(String username, String password, String name) {
        this("Account", username, password, name, false, "", "");
        initialLoginTime();
    }

    public Account(String role, String username, String password, String name, boolean banStatus, String loginDate, String loginTime) {
        this.role = role;
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

    // todo: เทียบเวลาด้วย comparable, rator มีสอนใน lab

    public boolean canLogin(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            initialLoginTime();
            return true;
        }
        return false;
    }

    public String getLoginDataTime() {
        return loginDate + " " + loginTime;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return loginDate + "-" + loginTime;
    }

    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void youGotBanned() {
        banStatus = true;
    }

    public boolean checkAccount(String username) {
        if (this.username.equals(username)) return true;
        return false;
    }

    public boolean isAdmin(){
        if(role.equals("Admin")) return true;
        return false;
    }

    public String toCsv() {
        return role + "," + username + "," + password + "," + name + ","
                + banStatus + "," + loginDate + "," + loginTime;
    }

    @Override
    public String toString() {
        String banStatus = "Active";
        if (this.banStatus == true) banStatus = "Baned";
        return username + " [" + banStatus + "]";
    }

    // todo: What user can do?
}
