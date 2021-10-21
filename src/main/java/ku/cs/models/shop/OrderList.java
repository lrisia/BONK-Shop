package ku.cs.models.shop;


import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderList;

    public OrderList() {
        orderList = new ArrayList<>();
    }

    public ArrayList<Order> getAllOrder() {
        return orderList;
    }

    public ArrayList<Order> getUnSendOrderList(String storeName) {
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order: orderList) {
            if (order.getTrackingNumber().equals("ยังไม่ส่ง") && order.getStoreName().equals(storeName))
                orders.add(order);
        } return orders;
    }

    public ArrayList<Order> getSendOrderList(String storeName) {
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order: orderList) {
            if ((!order.getTrackingNumber().equals("ยังไม่ส่ง")) && order.getStoreName().equals(storeName))
                orders.add(order);
        } return orders;
    }

    public Order searchOrderByOrder(Order order) {
        for (Order thisOrder: orderList) {
            if (thisOrder.equals(order)) return thisOrder;
        } return null;
    }

    public void addTrackingNumber(Order order, String trackingNumber) {
        Order thisOrder = searchOrderByOrder(order);
        thisOrder.setTrackingNumber(trackingNumber);
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public String toCsv() {
        String result = "";
        for (Order order: orderList) {
            result += order.toCsv() + "\n";
        }
        return result;
    }
}
