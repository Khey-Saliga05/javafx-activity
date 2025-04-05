package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label display;

    @FXML
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnDecimal, btnClear, btnBackspace;

    private final StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @FXML
    private void initialize() {
        display.setText("");
    }

    @FXML
    private void handleNumberButtonClick(javafx.event.ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText();


        if (isOperatorPressed || display.getText().equals("")) {
            isOperatorPressed = false;  // reset operator flag
        }


        currentInput.append(buttonText);
        display.setText(display.getText() + buttonText);
    }

    @FXML
    private void handleOperatorButtonClick(javafx.event.ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText();

        if (!currentInput.isEmpty()) {
            // Only process the operator if the current input is not empty
            if (!isOperatorPressed) {
                firstOperand = Double.parseDouble(currentInput.toString()); // Save the current operand
                operator = buttonText; // Store the operator
                display.setText(display.getText() + " " + buttonText + " "); // Append the operator to the display
                currentInput.setLength(0); // Clear the current input for the next operand
                isOperatorPressed = true;
            } else {
                // Replace the previous operator with the new one
                operator = buttonText;
                String updatedText = display.getText();
                display.setText(updatedText.substring(0, updatedText.lastIndexOf(" ")) + " " + operator + " ");
            }
        }
    }


    @FXML
    private void handleEqualsButtonClick(javafx.event.ActionEvent event) {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput.toString());
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        currentInput.setLength(0); // Clear input after error
                        return;
                    }
                    break;
            }
            display.setText(display.getText() + " = " + result); // Show the result in the expression
            currentInput.setLength(0); // Clear the input for the next calculation
            operator = ""; // Reset the operator
        }
    }

    @FXML
    private void handleClearButtonClick(javafx.event.ActionEvent event) {
        currentInput.setLength(0);
        display.setText("");
        firstOperand = 0;
        operator = "";
    }

    @FXML
    private void handleBackspaceButtonClick(javafx.event.ActionEvent event) {
        int length = currentInput.length();
        if (length > 0) {
            currentInput.deleteCharAt(length - 1);
            // Update the display by removing the last character from the expression
            String updatedText = display.getText();
            display.setText(updatedText.length() > 1 ? updatedText.substring(0, updatedText.length() - 1) : "");
        }
    }

    @FXML
    private void handleDecimalButtonClick(javafx.event.ActionEvent event) {
        // Prevent multiple decimal points in the input
        if (currentInput.indexOf(".") == -1) {
            currentInput.append(".");
            display.setText(display.getText() + ".");
        }
    }
}
