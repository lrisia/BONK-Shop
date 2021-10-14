package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class CategoryProductFilterer implements ProductFilterer{
    private String condition;

    public CategoryProductFilterer(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean filter(Product product) {
        if(product.getCategory().equals(condition)) return true;
        return false;
    }
}
