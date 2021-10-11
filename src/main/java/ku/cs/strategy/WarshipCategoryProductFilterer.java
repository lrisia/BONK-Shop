package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class WarshipCategoryProductFilterer implements ProductFilterer{
    @Override
    public boolean filter(Product product) {
        if (product.getCategory().equals("Warship")) return true;
        return false;
    }
}
