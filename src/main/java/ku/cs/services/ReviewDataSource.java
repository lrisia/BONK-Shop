package ku.cs.services;

import ku.cs.models.shop.Review;
import ku.cs.models.shop.ReviewList;
import ku.cs.models.verify.Account;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReviewDataSource implements DataSource<ReviewList> {
    private String directory;
    private String filename;

    public ReviewDataSource() { this("data", "reviewData.csv"); }

    public ReviewDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        FileService.initialFileIfNotExist(directory, filename);
    }

    @Override
    public ReviewList readData() {
        ReviewList reviewList = new ReviewList();
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String data[] = line.split(",");
                String reviewer = data[0];
                String productId = data[1];
                double score = Double.parseDouble(data[2]);
                String reviewDetail = data[3];
                reviewList.addNewReview(new Review(reviewer, productId, score, reviewDetail));
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
        return reviewList;
    }

    @Override
    public void writeData(ReviewList reviewList) {
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(reviewList.toCsv());
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
