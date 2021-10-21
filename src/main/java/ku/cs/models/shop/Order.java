package ku.cs.models.shop;

import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String productId;
    private String storeName;
    private String buyerUsername;
    private int amount;
    private double price;
    private String orderDate;
    private String orderTime;
    private String trackingNumber;
    private Product product;

    public Order(String productId, String storeName, String buyerUsername, int amount, String price) {
        this(productId, storeName, buyerUsername, amount, Double.parseDouble(price), "", "", "ยังไม่ส่ง");
        initialOrderTime();
    }

    public Order(String productId, String storeName, String buyerUsername, int amount, double price, String orderDate, String orderTime, String trackingNumber) {
        this.productId = productId;
        this.storeName = storeName;
        this.buyerUsername = buyerUsername;
        this.amount = amount;
        this.price = price;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.trackingNumber = trackingNumber;
        DataSource<ProductList> productListDataSource = new ProductDataSource();
        ProductList productList = productListDataSource.readData();
        this.product = productList.searchProductById(productId);
    }

    public void initialOrderTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        orderDate = time[0];
        orderTime = time[1];
    }

    public String generateTrackingNumber() {
        String result = "BO";
        for (String element: orderTime.split(":")) result += element;
        for (String element: orderDate.split("/")) result += element;
        return result + "TH";
    }

    public String getProductId() {
        return productId;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String toCsv() {
        return productId + "," + storeName + "," + buyerUsername + ","
                + amount + "," + price + "," + orderDate + "," + orderTime
                + "," + trackingNumber;
    }

    @Override
    public String toString() {
        return "ชื่อสินค้า " + product.getProductName() + " [" + trackingNumber +  "]\nจำนวน "
                + amount + " ชิ้น ราคาทั้งหมด " + String.format("%.2f", price) + " บาท";
    }
}
