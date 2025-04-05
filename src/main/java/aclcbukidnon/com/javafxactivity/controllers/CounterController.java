package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CounterController {

    public Label Counter;
    @FXML
    private Label lblCounter;

    @FXML
    private Button btnIncrement, btnDecrement;

    private int counter = 0;

    @FXML
    public void initialize() {
        updateCounterLabel();
    }

    @FXML
    private void increment() {
        counter++;
        updateCounterLabel();
    }

    @FXML
    private void decrement() {
        counter--;
        updateCounterLabel();
    }

    private void updateCounterLabel() {
        lblCounter.setText(String.valueOf(counter));
    }
}
