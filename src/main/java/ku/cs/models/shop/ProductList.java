package ku.cs.models.shop;

import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;

import java.util.ArrayList;

public class ProductList{
    private ArrayList<Product> productList;

    public ProductList() { productList = new ArrayList<>(); }

    public void addProduct(String shopName,String productName, double price, int stock, String description, String category) {
        Product product = new Product(shopName,productName,price,stock,description,category,initialProductId());
        productList.add(product);
    }

    public String initialProductId(){
        return String.format("%06d",productList.size()+1);
    }

    public ArrayList<Product> getProductList() { return productList; }

    public Product searchProductById(String id) {
        for (Product product: productList)
            if (product.checkId(id)) return product;
        return null;
    }

    public String toCsv() {
        String result = "";
        for (Product product: productList) {
            result += product.toCsv() + "\n";
        }
        return result;
    }
}
