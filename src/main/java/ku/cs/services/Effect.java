package ku.cs.services;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
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
}