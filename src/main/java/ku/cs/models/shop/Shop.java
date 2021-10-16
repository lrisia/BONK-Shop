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

    public Shop(Account buyer, Product product) {
        this.buyer = buyer;
        this.product = product;
        this.order = null;
    }

    public Shop(Account buyer) {
        this.buyer = buyer;
        this.product = null;
        this.order = null;
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

    public void newOrder(String productId, String storeName, String buyerUsername, int amount, String price) {
        order = new Order(productId, storeName, buyerUsername, amount, price);
    }
}
