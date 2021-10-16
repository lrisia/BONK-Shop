package ku.cs.strategy;

import ku.cs.models.shop.Product;

public class PriceProductFilterer implements ProductFilterer{
    private double max;
    private double min;

    public PriceProductFilterer(double max, double min) {
        this.max = max;
        this.min = min;
    }

    @Override
    public boolean filter(Product product) {
        double price = product.getPrice();
        if (price <= max && price >= min) return true;
        return false;
    }
}
