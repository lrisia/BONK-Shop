package ku.cs.models.shop;

import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;

import java.util.ArrayList;

<<<<<<< HEAD
import static ku.cs.controllers.userdata.ProfileController.fileSelected;

=======
>>>>>>> feature/profile_developer
public class ProductList{
    private ArrayList<Product> productList;

    public ProductList() { productList = new ArrayList<>(); }

<<<<<<< HEAD
    public void addProduct(String shopName,String productName, double price, int stock, String description, String category ) {
        String id = initialProductId();
        String imagePath = id + "-" + "product.png";
        Product product = new Product(shopName,productName,price,stock,description,category,id, imagePath);
        productList.add(product);
        product.setImagePath();
    }

    public void addProduct(Product product) {
=======
    public void addProduct(String shopName,String productName, double price, int stock, String description, String category) {
        Product product = new Product(shopName,productName,price,stock,description,category,initialProductId());
>>>>>>> feature/profile_developer
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
