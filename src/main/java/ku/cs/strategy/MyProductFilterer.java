package ku.cs.strategy;

import ku.cs.models.shop.Product;

public interface MyProductFilterer {
    boolean filter(Product product, String storeName);
}
