package ku.cs.models.shop;

public class Product {
    private String id;
    private String shopName;
    private String category;
    private String productName;
    private double price;
    private String detail;
    private String imagePath;
    private int stock;

    public Product(String shopName,String productName, double price, int stock, String detail, String category, String id){
        this.shopName = shopName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.detail = detail;
        this.category = category;
        this.id = id;
    }

    public boolean checkId(String id) {
        if (this.id.equals(id)) return true;
        return false;
    }
}
