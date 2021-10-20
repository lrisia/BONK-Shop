package ku.cs.models.shop;

import ku.cs.strategy.ProductFilterer;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
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
        double max = 0;
        if (productList.size() != 0)
            max = productList.get(0).getPrice();
        for (Product product: productList) {
            if (product.getPrice() > max) {
                max = product.getPrice();
            }
        } return max;
    }

    public int getMaxStock() {
        int max = 0;
        if (productList.size() != 0) max = productList.get(0).getStock();
        for (Product product: productList) {
            if (product.getStock() > max) max = product.getStock();
        } return max;
    }

    public void editProductInformation(Product oldProduct, String productName, String price, int amount, String detail) {
        Product product = searchProductById(oldProduct.getId());
        double priceDouble = Double.parseDouble(price);
        product.setProductInformation(productName, priceDouble, amount, detail);
    }

    public String initialProductId() {
        long time = System.currentTimeMillis();
        long num1 = time%1000000;
        long num2 = time/1000000;
        time = num2-num1;
        time *= time;
        return String.format("%06d", time%1000000);
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
        if (product == null) return null;
        return product.getImagePath();
    }

    public void removeProduct(Product remove) {
        Product product = searchProductById(remove.getId());
        productList.remove(product);
    }

    public String toCsv() {
        String result = "";
        for (Product product: productList) {
            result += product.toCsv() + "\n";
        }
        return result;
    }

}
