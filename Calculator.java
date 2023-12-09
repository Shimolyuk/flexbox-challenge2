package com.example.calculatorapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField displayField;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";

    @Override
    public void start(Stage primaryStage) {
        displayField = new TextField();
        displayField.setEditable(false);

        Button button0 = createNumberButton("0");
        Button button1 = createNumberButton("1");
        Button button2 = createNumberButton("2");
        Button button3 = createNumberButton("3");
        Button button4 = createNumberButton("4");
        Button button5 = createNumberButton("5");
        Button button6 = createNumberButton("6");
        Button button7 = createNumberButton("7");
        Button button8 = createNumberButton("8");
        Button button9 = createNumberButton("9");
        Button buttonDot = createNumberButton(".");
        Button buttonAdd = createOperatorButton("+");
        Button buttonSubtract = createOperatorButton("-");
        Button buttonMultiply = createOperatorButton("*");
        Button buttonDivide = createOperatorButton("/");
        Button buttonPercent = createOperatorButton("%");
        Button buttonClear = createClearButton("AC");
        Button buttonEquals = createEqualsButton("=");

        GridPane grid = new GridPane();
        grid.add(displayField, 0, 0, 4, 1);
        grid.add(button7, 0, 1);
        grid.add(button8, 1, 1);
        grid.add(button9, 2, 1);
        grid.add(buttonDivide, 3, 1);
        grid.add(button4, 0, 2);
        grid.add(button5, 1, 2);
        grid.add(button6, 2, 2);
        grid.add(buttonMultiply, 3, 2);
        grid.add(button1, 0, 3);
        grid.add(button2, 1, 3);
        grid.add(button3, 2, 3);
        grid.add(buttonSubtract, 3, 3);
        grid.add(button0, 0, 4);
        grid.add(buttonDot, 1, 4);
        grid.add(buttonPercent, 2, 4);
        grid.add(buttonAdd, 3, 4);
        grid.add(buttonClear, 0, 5);
        grid.add(buttonEquals, 1, 5, 3, 1);

        VBox vbox = new VBox(grid);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 250, 300);




        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createNumberButton(String anything) {
        Button button = new Button(anything);
        button.setOnAction(event -> onNumberButtonClick(anything));
        return button;
    }

    private Button createOperatorButton(String op) {
        Button button = new Button(op);
        button.setOnAction(event -> onOperatorButtonClick(op));
        return button;
    }

    private Button createClearButton(String label) {
        Button button = new Button(label);
        button.setOnAction(event -> onClearButtonClick());
        return button;
    }

    private Button createEqualsButton(String label) {
        Button button = new Button(label);
        button.setOnAction(event -> onEqualsButtonClick());
        return button;
    }


    private void onNumberButtonClick(String number) {
        currentInput += number;
        displayField.setText(currentInput);
    }

    private void onOperatorButtonClick(String op) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            currentInput = "";
            operator = op;
        }
    }

    private void onClearButtonClick() {
        currentInput = "";
        previousInput = "";
        operator = "";
        displayField.setText("");
    }

    private void onEqualsButtonClick() {
        if (!currentInput.isEmpty() && !previousInput.isEmpty() && !operator.isEmpty()) {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        displayField.setText("Error: Division by zero");
                        return;
                    }
                    break;
                case "%":
                    result = num1 % num2;
                    break;
            }

            displayField.setText(Double.toString(result));
            currentInput = Double.toString(result);
            previousInput = "";
            operator = "";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}