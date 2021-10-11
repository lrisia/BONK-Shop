package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class AssaultRifleCategoryProductFilterer implements ProductFilterer{
    @Override
    public boolean filter(Product product) {
        if(product.getCategory().equals("Assault rifle")) return true;
        return false;
    }
}
