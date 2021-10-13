package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class MyStoreProductFilterer implements MyProductFilterer{
    @Override
    public boolean filter(Product product, String storeName) {
        if(product.getShopName().equals(storeName)) return true;
        return false;
    }
}
