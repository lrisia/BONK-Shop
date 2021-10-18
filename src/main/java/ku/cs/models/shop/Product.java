package ku.cs.models.shop;

import ku.cs.services.HandleImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class Product implements HandleImage {
    private String id;
    private String shopName;
    private String category;
    private String productName;
    private double price;
    private String detail;
    private int stock;
    private String productAddDate;
    private String productAddTime;
    private String imagePath;

    public Product(String shopName, String productName, double price, int stock, String detail, String category, String id, String imagePath) {
        this(shopName, productName, price, stock, detail, category, id, "", "", imagePath);
        initialAddProductTime();
    }

    public Product(String shopName, String productName, double price, int stock, String detail, String category, String id, String productAddDate, String productAddTime, String imagePath) {
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

    public void purchase(int amount) {
        if (stock - amount < 0) return;
        stock -= amount;
    }

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

    public static void copyProductImageToPackage(File image, String imageName) {
        File file = new File("data/images/products");
        try {
            BufferedImage bi = ImageIO.read(image);
            ImageIO.write(bi, "png", new File(file.getAbsolutePath(), imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toCsv() {
        return shopName + "," + productName + "," + price + "," + stock + ","
                + detail + "," + category + "," + id + "," + productAddDate +
                "," + productAddTime + "," + imagePath;
    }

    public String getProductName() {
        return productName;
    }

    public String getDetail() { return detail; }

    public double getPrice() { return price; }

    public int getStock() { return stock; }

    public String getId() { return id; }

    public void setProductInformation(String productName, Double price, int amount, String detail) {
        this.productName = productName;
        this.price = price;
        this.stock = amount;
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public String getShopName() {
        return shopName;
    }

    @Override
    public void setImagePathToDirectory(String path) {
        String[] fileSplit = path.split("\\.");
        this.imagePath = getFilePictureName() + "." + fileSplit[fileSplit.length - 1];
    }

    @Override
    public String getFilePictureName() {
        return id + "-product";
    }
}
