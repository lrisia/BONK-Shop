package ku.cs.models.shop;

public class Review {
    private String reviewer;
    private String productId;
    private int score;
    private String reviewDetail;

    public Review(String reviewer, String productId, int score, String reviewDetail) {
        this.reviewer = reviewer;
        this.productId = productId;
        this.score = score;
        this.reviewDetail = reviewDetail;
    }
}
