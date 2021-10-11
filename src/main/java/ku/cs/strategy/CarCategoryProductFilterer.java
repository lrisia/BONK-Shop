package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class CarCategoryProductFilterer implements ProductFilterer{

    @Override
    public boolean filter(Product product) {
        if(product.getCategory().equals("Car")) return true;
        return false;
    }
}
