package ku.cs.models.shop;

public class Review {
    private String reviewer;
    private String productId;
    private double score;
    private String reviewDetail;

    public Review(String reviewer, String productId, double score, String reviewDetail) {
        this.reviewer = reviewer;
        this.productId = productId;
        this.score = score;
        this.reviewDetail = reviewDetail;
    }

    public void editData(double score, String reviewDetail) {
        this.score = score;
        this.reviewDetail = reviewDetail;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getProductId() {
        return productId;
    }

    public double getScore() {
        return score;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public String toCsv() {
        return reviewer + "," + productId + "," + score + "," + reviewDetail;
    }
}
