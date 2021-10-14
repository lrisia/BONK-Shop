package ku.cs.models.shop;

import ku.cs.models.verify.Account;

public class Shop {
    private Account buyer;
    private Product product;
    private Order order;

    public Shop(Account buyer, Product product, Order order) {
        this.buyer = buyer;
        this.product = product;
        this.order = order;
    }

    public Account getBuyer() {
        return buyer;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
