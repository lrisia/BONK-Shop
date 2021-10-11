package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class KnifeCategoryProductFilterer implements ProductFilterer{

    @Override
    public boolean filter(Product product) {
        if(product.getCategory().equals("Knife"))return true;
        return false;
    }
}
