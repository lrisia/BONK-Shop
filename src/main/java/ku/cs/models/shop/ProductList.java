package ku.cs.models.shop;

import ku.cs.strategy.ProductFilterer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductList{
    private ArrayList<Product> productList;

    public ProductList() { productList = new ArrayList<>(); }

    public void addProduct(String shopName,String productName, double price, int stock, String description, String category) {
        String id = initialProductId();
        String imagePath = id + "-" + "product.png";
        Product product = new Product(shopName, productName, price, stock, description, category, id, imagePath);
        productList.add(product);
        product.setImagePath();
    }

    public ProductList filter(ProductFilterer filterer) {
        ProductList filtered = new ProductList();
        for (Product product : productList)
            if (filterer.filter(product)) filtered.addProduct(product);
        return filtered;
    }

    public void sort(Comparator<Product> productComparator) {
        Collections.sort(this.productList, productComparator);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void purchaseProduct(String productId, int amount) {
        Product product = searchProductById(productId);
        product.purchase(amount);
    }

    public double getMaxPrice() {
        double max = productList.get(0).getPrice();
        for (Product product: productList) {
            if (product.getPrice() > max) {
                max = product.getPrice();
            }
        } return max;
    }

    public int getMaxStock() {
        int max = productList.get(0).getStock();
        for (Product product: productList) {
            if (product.getStock() > max) max = product.getStock();
        } return max;
    }

    public void editProductInformation(Product oldProduct, String productName, String price, int amount, String detail) {
        Product product = searchProductById(oldProduct.getId());
        double priceDouble = Double.parseDouble(price);
        product.setProductInformation(productName, priceDouble, amount, detail);
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

    public String getStoreNameByProductId(String productId) {
        Product product = searchProductById(productId);
        return product.getShopName();
    }

    public String getProductImagePathByProductId(String productId) {
        Product product = searchProductById(productId);
        return product.getImagePath();
    }

    public String toCsv() {
        String result = "";
        for (Product product: productList) {
            result += product.toCsv() + "\n";
        }
        return result;
    }

}
