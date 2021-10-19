package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.shop.Review;

public class ReviewController {
    @FXML private Label nameLabel;
    @FXML private Label scoreLabel;
    @FXML private TextArea reviewTextArea;
    @FXML private ImageView moreActionIconImageView;

    @FXML
    public void initialize() {
        moreActionIconImageView.setImage(new Image(getClass().getResource("/images/three_dot_icon.png").toExternalForm()));
    }

    @FXML
    public void setData(Review review) {
        nameLabel.setText(review.getReviewer());
        scoreLabel.setText(String.format("%.1f", review.getScore()));
        reviewTextArea.setText(review.getReviewDetail());
    }
}
