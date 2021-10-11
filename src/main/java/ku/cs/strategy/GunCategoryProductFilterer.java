package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class GunCategoryProductFilterer implements ProductFilterer{

    @Override
    public boolean filter(Product product) {
        if(product.getCategory().equals("Gun"))return true;
        return false;
    }
}
