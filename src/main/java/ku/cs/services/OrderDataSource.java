package ku.cs.services;

import ku.cs.models.shop.Order;
import ku.cs.models.shop.OrderList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class OrderDataSource implements DataSource<OrderList>{
    private String directory;
    private String filename;

    public OrderDataSource() {
        this("data", "orderData.csv");
    }

    public OrderDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        FileService.initialFileIfNotExist(directory, filename);
    }

    @Override
    public OrderList readData() {
        OrderList orderList = new OrderList();
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String productId = data[0];
                String storeName = data[1];
                String buyerName = data[2];
                int amount = Integer.parseInt(data[3]);
                double price = Double.parseDouble(data[4]);
                String orderDate = data[5];
                String orderTime = data[6];
                String trackingNumber = data[7];
                orderList.addOrder(new Order(productId, storeName, buyerName, amount, price, orderDate, orderTime, trackingNumber));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }

    @Override
    public void writeData(OrderList orderList) {
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(orderList.toCsv());
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
