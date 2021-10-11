package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class PlaneCategoryProductFilterer implements ProductFilterer{

    @Override
    public boolean filter(Product product) {
        if(product.getCategory().equals("Plane")) return true;
        return false;
    }
}
