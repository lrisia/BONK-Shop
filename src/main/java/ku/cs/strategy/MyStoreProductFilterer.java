package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class MyStoreProductFilterer implements ProductFilterer {
    private String storeName;

    public MyStoreProductFilterer(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean filter(Product product) {
        if(product.getShopName().equals(storeName)) return true;
        return false;
    }
}
