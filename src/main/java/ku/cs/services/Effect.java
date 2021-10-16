package ku.cs.services;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Effect {
    public void fadeOutLabelEffect(Label fadeLabel, double duration) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(duration), fadeLabel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
    }

    public void fadeInLabelEffect(Label fadeLabel, double duration) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(duration), fadeLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void crossFadeTransitionLabel(Label label1, Label label2, double duration) {
        label2.setOpacity(0);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(duration), label1);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished((ActionEvent event) -> {
            fadeInLabelEffect(label2, duration);
        });
        fadeOut.play();
    }

    public void stopAllEffect() {

    }

    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;
            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();
            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }
            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;
            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);
        }
    }
}
