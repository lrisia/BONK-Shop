package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class SpecificProductFilterer implements ProductFilterer{
    private String storeName;

    public SpecificProductFilterer(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean filter(Product product) {
        if(product.getShopName().equals(storeName)) return true;
        return false;
    }
}
