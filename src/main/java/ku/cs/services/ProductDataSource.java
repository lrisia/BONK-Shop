package ku.cs.services;

import ku.cs.models.shop.ProductList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProductDataSource implements DataSource<ProductList>{
    private String directory;
    private String filename;

    public ProductDataSource() {
        this("data", "productData.csv");
    }

    public ProductDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        InitialFileIfNotExist.initialFileIfNotExist(directory, filename);
    }

    @Override
    public ProductList readData() {
        ProductList productList = new ProductList();
        productList.addProduct("shopName","productName",0,0,"productDetail","category");
        return productList;
    }

    @Override
    public void writeData(ProductList productList) {
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(productList.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
