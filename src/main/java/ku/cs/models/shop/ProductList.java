package ku.cs.models.shop;

import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> productList;

    public ProductList() { productList = new ArrayList<>(); }

    public void addProduct(Product product) { productList.add(product); }

    public ArrayList<Product> getProductList() { return productList; }

    public Product searchProductById(String id) {
        for (Product product: productList)
            if (product.checkId(id)) return product;
        return null;
    }
}
