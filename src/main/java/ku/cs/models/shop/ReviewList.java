package ku.cs.models.shop;

import java.util.ArrayList;

public class ReviewList {
    private ArrayList<Review> reviewsList;

    public ReviewList() { reviewsList = new ArrayList<>(); }

    public void addNewReview(Review review) {
        reviewsList.add(review);
    }
}
