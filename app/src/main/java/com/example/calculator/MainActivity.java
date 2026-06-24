package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

    private String currentInput = "";
    private String expression = "";

    private double firstNumber = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9
        };

        for (int id : numberButtons) {
            Button btn = findViewById(id);

            btn.setOnClickListener(v -> {
                String value = btn.getText().toString();

                currentInput += value;
                expression += value;

                tvDisplay.setText(expression);
            });
        }

        findViewById(R.id.btnDot).setOnClickListener(v -> {
            currentInput += ".";
            expression += ".";
            tvDisplay.setText(expression);
        });

        findViewById(R.id.btnAC).setOnClickListener(v -> {
            currentInput = "";
            expression = "";
            firstNumber = 0;
            operator = "";

            tvDisplay.setText("0");
        });

        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSub).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setOperator("/"));

        findViewById(R.id.btnEqual).setOnClickListener(v -> calculate());
    }

    private void setOperator(String op) {

        if (!currentInput.isEmpty()) {

            firstNumber = Double.parseDouble(currentInput);
            operator = op;

            if (op.equals("*"))
                expression += "×";
            else if (op.equals("/"))
                expression += "÷";
            else
                expression += op;

            tvDisplay.setText(expression);

            currentInput = "";
        }
    }

    private void calculate() {

        if (currentInput.isEmpty())
            return;

        double secondNumber = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {

            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":

                if (secondNumber == 0) {
                    tvDisplay.setText("Error");
                    currentInput = "";
                    expression = "";
                    return;
                }

                result = firstNumber / secondNumber;
                break;
        }

        String finalResult;

        if (result == (long) result) {
            finalResult = String.valueOf((long) result);
        } else {
            finalResult = String.valueOf(result);
        }

        tvDisplay.setText(finalResult);

        currentInput = finalResult;
        expression = finalResult;
    }
}