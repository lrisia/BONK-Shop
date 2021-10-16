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

    public void purchaseSuccessful(Order order) {

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
