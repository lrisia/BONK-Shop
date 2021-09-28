package ku.cs.services;

import java.io.File;
import java.io.IOException;

public class ProductDataSource implements DataSource {
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
    public Object readData() {
        return null;
    }

    @Override
    public void writeData(Object o) {

    }


}
