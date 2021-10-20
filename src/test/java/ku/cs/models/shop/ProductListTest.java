package ku.cs.models.shop;

import ku.cs.services.DataSource;
import ku.cs.services.ProductDataSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductListTest {
    @Test
    void testGetMaxProductPrice() {
        DataSource<ProductList> dataSource = new ProductDataSource();
        ProductList productList = dataSource.readData();

        assertEquals(0, productList.getMaxPrice());
    }

}