package ku.cs.controllers.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class WriteReportController {
    @FXML private Label usernameLabel;
    @FXML private Spinner<Double> scoreSpinner;
    @FXML private TextArea reviewDetailTextArea;

    @FXML
    public void initialize() {
        SpinnerValueFactory<Double> valueFactoryScore = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,5);
        valueFactoryScore.setValue(5.0);
        scoreSpinner.setValueFactory(valueFactoryScore);
    }

    public void setData(String username) {
        usernameLabel.setText(username);
    }
}
