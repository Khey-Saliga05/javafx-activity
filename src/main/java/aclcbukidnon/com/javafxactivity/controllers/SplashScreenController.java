package aclcbukidnon.com.javafxactivity.controllers;

import aclcbukidnon.com.javafxactivity.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenController {


    private Timer timer;

    private Stage stage;



    @FXML
    public ProgressBar progressInitial;


    @FXML
    public void initialize() {
        var timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> progressInitial.setProgress(0)),
                new KeyFrame(Duration.millis(300), e -> progressInitial.setProgress(0.05)), // Slow start
                new KeyFrame(Duration.millis(600), e -> progressInitial.setProgress(0.2)),  // Gradual rise
                new KeyFrame(Duration.millis(750), e -> progressInitial.setProgress(0.5)),  // Speed up
                new KeyFrame(Duration.millis(900), e -> progressInitial.setProgress(0.8)),  // Faster!
                new KeyFrame(Duration.millis(1100), e -> progressInitial.setProgress(1.1)), // Overshoot
                new KeyFrame(Duration.millis(1250), e -> progressInitial.setProgress(1.0))  // Settle back
        );

        timeline.play();
        timeline.setOnFinished(_ -> onCompleted());
    }


    public void onCompleted () {
        var dashboardFxml = new FXMLLoader(Main.class.getResource("dashboard-view.fxml"));
        try {
            var scene = new Scene(dashboardFxml.load(), 800, 600);
            System.out.println(stage.toString());
            this.stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setStage(Stage stage) {
        System.out.println("Set Stage");
        this.stage = stage;
        System.out.println(stage.toString());
    }
}
