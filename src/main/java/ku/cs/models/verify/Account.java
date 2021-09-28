package ku.cs.models.verify;

import ku.cs.models.shop.ProductList;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class Account {
    private String role;
    private String name;
    private String username;
    private String password;
    private boolean banStatus;
    private int tryLoginWhenBanned;
    private String loginDate;
    private String loginTime;
    private String imagePath;
    private ProductList productList;

    public Account(String username, String password, String name) {
        this("Account", username, password, name, false, "", "","profileDefault.png");
        initialLoginTime();
    }

    public Account(String role, String username, String password, String name, boolean banStatus, String loginDate, String loginTime,String ImagePath) {
        this.role = role;
        this.name = name;
        this.username = username;
        this.password = password;
        this.banStatus = banStatus;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        tryLoginWhenBanned = 0;
        this.imagePath = ImagePath;
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
            tryLoginWhenBanned = 0;
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

    public int getTryLoginWhenBanned() {
        return tryLoginWhenBanned;
    }

    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void switchBanStatus() {
        if (banStatus) banStatus = false;
        else banStatus = true;
    }

    public boolean checkAccount(String username) {
        if (this.username.equals(username)) return true;
        return false;
    }

    public boolean gotBanned() {
        tryLoginWhenBanned += 1;
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
        return role + "," + username + "," + password + "," + name + ","
                + banStatus + "," + loginDate + "," + loginTime + "," + imagePath;
    }

    @Override
    public String toString() {
        String banStatus = "Active";
        if (this.banStatus == true) banStatus = "Banned";
        return username + " [" + banStatus + "]";
    }

    public void setImagePath() {
        if (fileSelected != null) {
            imagePath = username + "-" +
                    LocalDate.now().getYear() + "-"
                    + LocalDate.now().getMonth() + "-"
                    + LocalDate.now().getDayOfMonth() + ".png";
            copyUserImageToPackage(fileSelected, imagePath);
        }
        else {
            imagePath = "profileDefault.png";
        }
    }

    public String getImagePath() {
        return new File(System.getProperty("user.dir") +
                File.separator +
                "data/PictureData" +
                File.separator +
                imagePath).toURI().toString();
    }

    public static void copyUserImageToPackage(File image, String imageName) {
        File file = new File("data/PictureData");
        try {
            BufferedImage bi = ImageIO.read(image);
            ImageIO.write(bi, "png", new File(file.getAbsolutePath(), imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // todo: What user can do?
}
