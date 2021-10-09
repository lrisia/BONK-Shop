package ku.cs.models.shop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
=======
>>>>>>> feature/profile_developer

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class Product {
    private String id;
    private String shopName;
    private String category;
    private String productName;
    private double price;
    private String detail;
    private int stock;
<<<<<<< HEAD
    private String productAddDate;
    private String productAddTime;
    private String imagePath;

    public Product(String shopName, String productName, double price, int stock, String detail, String category, String id, String imagePath) {
        this(shopName, productName, price, stock, detail, category, id, "", "", imagePath);
        initialAddProductTime();
    }

    public Product(String shopName, String productName, double price, int stock, String detail, String category, String id, String productAddDate, String productAddTime, String imagePath) {
=======
    private String imagePath;

    public Product(String shopName, String productName, double price, int stock, String detail, String category, String id) {
>>>>>>> feature/profile_developer
        this.shopName = shopName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.detail = detail;
        this.category = category;
        this.id = id;
        this.productAddDate = productAddDate;
        this.productAddTime = productAddTime;
        this.imagePath = imagePath;
    }

    public String getTime() {
        return productAddDate + "-" + productAddTime;
    }




    public boolean checkId(String id) {
        if (this.id.equals(id)) return true;
        return false;
    }

<<<<<<< HEAD
    public void initialAddProductTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        productAddDate = time[0];
        productAddTime = time[1];
    }

    public void setImagePath() {
        if (fileSelected != null) {
            imagePath = id + "-" + "product.png";
            copyProductImageToPackage(fileSelected, imagePath);
=======
    public void setImagePath() {
        if (fileSelected != null) {
            imagePath = id + "-" + "product.png";
            copyUserImageToPackage(fileSelected, imagePath);
>>>>>>> feature/profile_developer
        }
        else {
            imagePath = "product_default_white.png";
        }
    }

    public String getImagePath() {
        return new File(System.getProperty("user.dir") +
                File.separator +
                "data/images/products" +
                File.separator +
                imagePath).toURI().toString();
    }

<<<<<<< HEAD
    public static void copyProductImageToPackage(File image, String imageName) {
=======
    public static void copyUserImageToPackage(File image, String imageName) {
>>>>>>> feature/profile_developer
        File file = new File("data/images/products");
        try {
            BufferedImage bi = ImageIO.read(image);
            ImageIO.write(bi, "png", new File(file.getAbsolutePath(), imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
<<<<<<< HEAD
        return shopName + "," + productName + "," + price + "," + stock + ","
                + detail + "," + category + "," + id + "," + productAddDate +
                "," + productAddTime + "," + imagePath;
=======
        return shopName + "," + productName + "," + price + "," + stock + "," + detail + "," + category + "," + id + "," + imagePath;
>>>>>>> feature/profile_developer
    }
}
