package ku.cs.models.shop;

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

    public Order(String productId, String storeName, String buyerUsername, int amount, String price) {
        this(productId, storeName, buyerUsername, amount, Double.parseDouble(price), "", "", "");
        initialOrderTime();
        this.trackingNumber = generateTrackingNumber();
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

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String toCsv() {
        return productId + "," + storeName + "," + buyerUsername + ","
                + amount + "," + price + "," + orderDate + "," + orderTime
                + "," + trackingNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "productId='" + productId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", buyerName='" + buyerUsername + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}
