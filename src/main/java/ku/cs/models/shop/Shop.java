package ku.cs.models.shop;

import ku.cs.models.verify.Account;

public class Shop {
    private String shopName;
    private Account owner;
    private ProductList productList;

    public Shop(String shopName, Account owner, ProductList productList) {
        this.shopName = shopName;
        this.owner = owner;
        this.productList = productList;
    }

}
