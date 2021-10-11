package ku.cs.strategy;

import ku.cs.models.shop.Product;

public interface ProductFilterer {
    boolean filter(Product product);
}
