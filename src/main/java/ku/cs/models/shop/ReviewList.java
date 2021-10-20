package ku.cs.models.shop;

import ku.cs.models.verify.Report;

import java.util.ArrayList;

public class ReviewList {
    private ArrayList<Review> reviewsList;

    public ReviewList() { reviewsList = new ArrayList<>(); }

    public void addNewReview(Review review) {
        reviewsList.add(review);
    }

    public ArrayList<Review> getAllReview() {
        ArrayList<Review> reviews = new ArrayList<>();
        for (Review review: reviewsList) {
            reviews.add(review);
        } return reviews;
    }

    public String toCsv() {
        String result = "";
        for (Review review: reviewsList) result += review.toCsv() + "\n";
        return result;
    }

    public ArrayList<Review> getAllReviewWithProductId(String id) {
        ArrayList<Review> filtered = new ArrayList<>();
        for (Review review: reviewsList)
            if (review.getProductId().equals(id)) filtered.add(review);
         return filtered;
    }

    public double findAverageScore(ArrayList<Review> reviewsList) {
        double total = 0;
        for (Review review: reviewsList) {
            total += review.getScore();
        }
        return total/reviewsList.size();
    }

    public Review searchReviewByUsername(String username, String productId) {
        for (Review review: reviewsList)
            if (review.getReviewer().equals(username) && review.getProductId().equals(productId)) return review;
        return null;
    }

    public boolean isThisUsernameHaveAlreadyReview(String username, String productId) {
        if (searchReviewByUsername(username, productId) == null) return false;
        return true;
    }

    public void editReviewInformation(String username, String productId, String score, String reviewDetail) {
        Review review = searchReviewByUsername(username, productId);
        review.editData(Double.parseDouble(score), reviewDetail);
    }

    public void removeAllReviewByProductId(String productId) {
        ArrayList<Review> reviews = new ArrayList<>();
        for (Review review: reviewsList) {
            if (review.getProductId().equals(productId))
                reviews.add(review);
        } for (Review review: reviews) {
            if (review.getProductId().equals(productId)) reviewsList.remove(review);
        }
    }
}
