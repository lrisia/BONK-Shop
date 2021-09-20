package ku.cs.models.shop;

public class Product {
    private String id;
    private String sellerName;
    private String category;
    private String productName;
    private double price;
    private String caption;



    public boolean checkId(String id) {
        if (this.id.equals(id)) return true;
        return false;
    }
}
