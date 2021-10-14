package ku.cs.models.shop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderDate;
    private String orderTime;
    private String buyerName;
    private String storeName;
    private Product product;
    private int amount;
    private double price;
    private String trackingNumber;

    public Order(String buyerName, String storeName, Product product, int amount, String price) {
        initialOrderTime();
        this.buyerName = buyerName;
        this.storeName = storeName;
        this.product = product;
        this.amount = amount;
        this.price = Double.parseDouble(price);
        this.trackingNumber = generateTrackingNumber();
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

    @Override
    public String toString() {
        return "Order{" +
                "orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", product=" + product +
                ", amount=" + amount +
                ", price=" + price +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}
