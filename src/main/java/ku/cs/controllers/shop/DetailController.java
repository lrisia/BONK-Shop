package ku.cs.controllers.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ku.cs.models.shop.*;
import ku.cs.models.verify.Account;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;
import com.github.saacsos.FXRouter;
import ku.cs.services.ReviewDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class DetailController {
    // หน้ารายละเอียดสินค้า
    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private Label maxProductLabel;
    @FXML private Label productTotalLabel;
    @FXML private Label priceTotalLabel;
    @FXML private Label categoryLabel;
    @FXML private TextArea detailTextArea;
    @FXML private Label productStoreNameLabel;
    @FXML private Spinner<Integer> productPieceSpinner;
    @FXML private Button buyGoodBtn;
    // หน้ารีวิว
    @FXML private GridPane reviewGridPane;
    @FXML private Pane writeReviewPane;
    @FXML private Pane noReviewPane;
    @FXML private Label usernameLabel;
    @FXML private Label notificationLabel;
    @FXML private Label allScoreLabel;
    @FXML private TextArea reviewDetailTextArea;
    @FXML private Spinner<Integer> scoreSpinner;
    private SpinnerValueFactory<Integer> valueFactoryScore;
    private SpinnerValueFactory<Integer> valueFactoryPiece;

    private Shop shop;
    private Product item; //เก็บข้อมูลจากหน้าสินค้ามาไว้ในนี้ด้วย
    private Account account;
    private DataSource<ReviewList> reviewDataSource;
    private ReviewList reviewList;
    private Effect effect;
    private int currentPiece;

    public void initialize(){
        readData();
        int startingAmount = 1;
        if (item.getStock() == 0) {
            buyGoodBtn.setStyle("-fx-background-color: market-place-background");
            buyGoodBtn.setText("สินค้าหมด");
            startingAmount = 0;
        } currentPiece = startingAmount;
        initializeSpinner(startingAmount);
        initializeUI();
        showAllReview(reviewList);
    }

    private void readData() {
        shop = (Shop) FXRouter.getData();
        item = shop.getProduct(); //เก็บข้อมูลจากหน้าสินค้ามาไว้ในนี้ด้วย
        account = shop.getBuyer();
        reviewDataSource = new ReviewDataSource();
        reviewList = reviewDataSource.readData();
    }

    private void initializeUI() {
        effect = new Effect();
        writeReviewPane.setDisable(true);
        detailTextArea.setWrapText(true);
        writeReviewPane.setOpacity(0);
        productImageView.setImage(new Image(item.getImagePath()));
        effect.centerImage(productImageView);
        usernameLabel.setText(account.getUsername());
        productStoreNameLabel.setText(item.getShopName());
        productNameLabel.setText(item.getProductName());
        priceLabel.setText(String.format("%.2f", item.getPrice())+" ฿");
        categoryLabel.setText(item.getCategory());
        detailTextArea.setText(item.getDetail());
        productTotalLabel.setText("มีสินค้า "+item.getStock()+" ชิ้น");
        priceTotalLabel.setText(("ทั้งหมดราคา "+ String.format("%.2f", currentPiece*item.getPrice()) +" บาท"));
    }

    private void initializeSpinner(int startingAmount) {
        valueFactoryScore = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5);
        valueFactoryScore.setValue(5);
        scoreSpinner.setValueFactory(valueFactoryScore);
        valueFactoryPiece = new SpinnerValueFactory.IntegerSpinnerValueFactory(startingAmount, item.getStock()+1);
        valueFactoryPiece.setValue(startingAmount);
        productPieceSpinner.setValueFactory(valueFactoryPiece);
        productPieceSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if (t1 == item.getStock()+1) {
                    effect.fadeOutLabelEffect(maxProductLabel, 3);
                    valueFactoryPiece.setValue(item.getStock());
                    productPieceSpinner.setValueFactory(valueFactoryPiece);
                }
                currentPiece = productPieceSpinner.getValue();
                priceTotalLabel.setText(("ทั้งหมดราคา " + String.format("%.2f",currentPiece*item.getPrice()) + " บาท"));
            }
        });
    }

    private void initializeWriteReviewWindow(String username, String productId) {
        if (reviewList.isThisUsernameHaveAlreadyReview(username, productId)) {
            Review thisReview = reviewList.searchReviewByUsername(username, productId);
            valueFactoryScore.setValue((int)thisReview.getScore());
            scoreSpinner.setValueFactory(valueFactoryScore);
            reviewDetailTextArea.setText(thisReview.getReviewDetail());
        }
    }

    private void showAllReview(ReviewList reviewList) {
        reviewGridPane.getChildren().clear();
        ArrayList<Review> reviews = reviewList.getAllReviewWithProductId(item.getId());
        int allReview = reviews.size();
        String averageScore = String.format("%.1f", reviewList.findAverageScore(reviews)) + "/5.0";
        noReviewPane.setOpacity(0);
        if (allReview == 0)  {
            averageScore = "ไม่มีคะแนน";
            noReviewPane.setOpacity(0.45);
        }
        allScoreLabel.setText("ทั้งหมด " + allReview + " รีวิว คะแนนเฉลี่ย " + averageScore);
        int column = 0;
        int row = 1;
        try {
            for (Review review: reviews) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shop/review.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ReviewController reviewController = fxmlLoader.getController();
                reviewController.setData(review);
                if(column == 1){
                    column = 0;
                    row++;
                }
                reviewGridPane.add(anchorPane,column++, row);
                GridPane.setMargin(anchorPane, new Insets(9));
                GridPane.setColumnSpan(anchorPane, 3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleWriteReviewButton() {
        writeReviewPane.setOpacity(1);
        writeReviewPane.setDisable(false);
        effect.fadeInPage(writeReviewPane);
        initializeWriteReviewWindow(account.getUsername(), item.getId());
    }

    @FXML
    public void handleCloseWriteReview() {
        writeReviewPane.setOpacity(0);
        writeReviewPane.setDisable(true);
    }

    @FXML
    public void handleEnterWriteReviewButton() {
        String username = account.getUsername();
        String productId = item.getId();
        if (reviewDetailTextArea.getText().equals("")) {
            notificationLabel.setText("ยังไม่ได้กรอกรายละเอียด");
        } else if (reviewList.isThisUsernameHaveAlreadyReview(username, productId)) {
            String score = scoreSpinner.getValue().toString();
            String detail = reviewDetailTextArea.getText();
            reviewList.editReviewInformation(username, productId, score, detail);
            reviewDataSource.writeData(reviewList);
            handleCloseWriteReview();
            showAllReview(reviewList);
        } else {
            reviewList.addNewReview(new Review(username, productId, scoreSpinner.getValue(), reviewDetailTextArea.getText()));
            reviewDataSource.writeData(reviewList);
            handleCloseWriteReview();
            showAllReview(reviewList);
        } effect.fadeOutLabelEffect(notificationLabel, 3);
    }

    @FXML
    public void buyGoods() {
        if ((!(item.getStock() == 0)) && currentPiece <= item.getStock()) {
            try {
                String price = String.format("%.2f", currentPiece*item.getPrice());
                shop.newOrder(item.getId(), item.getShopName(), account.getUsername(), currentPiece, price);
                FXRouter.goTo("summary", shop);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า summary ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void switchToHome() {
        try {
            FXRouter.goTo("main", new Shop(account));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า main ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void switchToSpecific() {
        try {
            FXRouter.goTo("specific", new Shop(account, item));
        } catch (IOException e) {
            System.err.println("ไปที่หน้า specific ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void goToReportPage() {
        try {
            FXRouter.goTo("report", shop);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
