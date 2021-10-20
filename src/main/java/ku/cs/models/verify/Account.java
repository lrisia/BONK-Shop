package ku.cs.models.verify;

import ku.cs.services.HandleImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class Account implements HandleImage {
    private String role;
    private String name;
    private String username;
    private String password;
    private String storeName;
    private int lowProductAlert;
    private boolean banStatus;
    private int tryLoginWhenGotBanned;
    private String loginDate;
    private String loginTime;
    private String imagePath;

    public Account(String username, String password, String name) {
        this("Account", username, password, name, "-", 0, false, 0, "", "","profileDefault.png");
        initialLoginTime();
    }

    public Account(String role, String username, String password, String name, String storeName, int lowProductAlert, boolean banStatus, int tryLoginWhenGotBanned, String loginDate, String loginTime,String imagePath) {
        this.role = role;
        this.name = name;
        this.username = username;
        this.password = password;
        this.storeName = storeName;
        this.lowProductAlert = lowProductAlert;
        this.banStatus = banStatus;
        this.tryLoginWhenGotBanned = tryLoginWhenGotBanned;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.imagePath = imagePath;
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
            if (gotBanned()) tryLoginWhenGotBanned += 1;
            initialLoginTime();
            return true;
        }
        return false;
    }

    public String getLoginDateTime() {
        return loginDate + " " + loginTime;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public int getLowProductAlert() {
        return lowProductAlert;
    }

    public String getName() {
        return name;
    }

    public void setLowProductAlert(int lowProductAlert) {
        this.lowProductAlert = lowProductAlert;
    }

    public LocalDateTime getTime() {
        String[] data = loginDate.split("/");
        int year = Integer.parseInt(data[2]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[0]);
        data = loginTime.split(":");
        int hour = Integer.parseInt(data[0]);
        int minute = Integer.parseInt(data[1]);
        int sec = Integer.parseInt(data[2]);
        return LocalDateTime.of(year, month, day, hour, minute, sec);
    }

    public String getStoreName() { return storeName; }

    public int getTryLoginWhenGotBanned() { return tryLoginWhenGotBanned; }

    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void switchBanStatus() {
        if (banStatus) banStatus = false;
        else banStatus = true;
        tryLoginWhenGotBanned = 0;
    }

    public boolean checkAccount(String username) {
        if (this.username.equals(username)) return true;
        return false;
    }

    public boolean gotBanned() {
        if (banStatus) return true;
        return false;
    }

    public boolean isAdmin(){
        if(role.equals("Admin")) return true;
        return false;
    }

    public boolean isSeller() {
        if(role.equals("Seller")) return true;
        return false;
    }


    public String toCsv() {
        return role + "," + username + "," + password + "," + name + "," + storeName + "," + lowProductAlert + ","
                + banStatus + "," + tryLoginWhenGotBanned +"," + loginDate + "," + loginTime + "," + imagePath;
    }

    @Override
    public String toString() {
        String banStatus = "ปกติ";
        if (this.banStatus == true) banStatus = "ถูกแบน";
        return "ชื่อบัญชีผู้ใช้: " + username + " [สถานะ: " + banStatus + "]\nเข้าใช้งานล่าสุด: " + getLoginDateTime();
    }

    public void setImagePath() {
        if (fileSelected != null) {
            imagePath = username + "-" + "profile.png";
            copyUserImageToPackage(fileSelected, imagePath);
        }
        else {
            imagePath = "src/main/resources/images/profileDefault.png";
        }
    }

    public String getImagePath() {
        return new File(System.getProperty("user.dir") +
                File.separator +
                "data/images/profiles" +
                File.separator +
                imagePath).toURI().toString();
    }

    public void copyUserImageToPackage(File image, String imageName) {
        File file = new File("data/images/profiles");
        try {
            BufferedImage bi = ImageIO.read(image);
            ImageIO.write(bi, "png", new File(file.getAbsolutePath(), imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerNewStore(String storeName){
        this.storeName = storeName;
        this.role = "Seller";
    }

    @Override
    public void setImagePathToDirectory(String path) {
        String[] fileSplit = path.split("\\.");
        this.imagePath = getFilePictureName() + "." + fileSplit[fileSplit.length - 1];
    }

    @Override
    public String getFilePictureName() {
        return username + "-profile";
    }

    // todo: What user can do?
}