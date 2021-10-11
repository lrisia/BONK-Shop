package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class TankCategoryProductFilterer implements ProductFilterer{

    @Override
    public boolean filter(Product product) {
        if (product.getCategory().equals("Tank")) return true;
        return false;
    }
}
