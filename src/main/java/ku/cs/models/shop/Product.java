package ku.cs.models.shop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ku.cs.controllers.userdata.ProfileController.fileSelected;

public class Product {
    private String id;
    private String shopName;
    private String category;
    private String productName;
    private double price;
    private String detail;
    private int stock;
    private String imagePath;

    public Product(String shopName, String productName, double price, int stock, String detail, String category, String id, String imagePath) {
        this.shopName = shopName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.detail = detail;
        this.category = category;
        this.id = id;
        this.imagePath = imagePath;
    }
    public boolean checkId(String id) {
        if (this.id.equals(id)) return true;
        return false;
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
        return shopName + "," + productName + "," + price + "," + stock + "," + detail + "," + category + "," + id + "," + imagePath;
    }
}
