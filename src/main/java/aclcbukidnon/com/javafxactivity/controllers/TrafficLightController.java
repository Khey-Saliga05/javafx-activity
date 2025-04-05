package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TrafficLightController {

    @FXML
    private Circle redLight;
    @FXML
    private Circle yellowLight;
    @FXML
    private Circle greenLight;

    private int currentLight = 0;


    public void initialize() {
        System.out.println("Red Light: " + redLight);
        System.out.println("Yellow Light: " + yellowLight);
        System.out.println("Green Light: " + greenLight);

        startTrafficLightCycle();
    }

    private void startTrafficLightCycle() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> changeLight()),
                new KeyFrame(Duration.seconds(6), e -> changeLight()),
                new KeyFrame(Duration.seconds(9), e -> changeLight())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void changeLight() {
        resetLights();
        switch (currentLight) {
            case 0:
                System.out.println("Switching to Red");
                setActiveLight(redLight, Color.RED);
                break;
            case 1:
                System.out.println("Switching to Yellow");
                setActiveLight(yellowLight, Color.YELLOW);
                break;
            case 2:
                System.out.println("Switching to Green");
                setActiveLight(greenLight, Color.GREEN);
                break;
        }
        currentLight = (currentLight + 1) % 3;
    }

    private void resetLights() {
        redLight.setFill(Color.GRAY);
        yellowLight.setFill(Color.GRAY);
        greenLight.setFill(Color.GRAY);

        redLight.setEffect(null);
        yellowLight.setEffect(null);
        greenLight.setEffect(null);
    }

    private void setActiveLight(Circle light, Color color) {
        light.setFill(color);

    }
}
