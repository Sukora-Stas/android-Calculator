package com.androiddev.bsuir.android_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView result;
    EditText number;


    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        result.append(lastOperation);
    }

    private void initView() {
        result = (TextView) findViewById(R.id.textResult);
        number = (EditText) findViewById(R.id.editText);
        number.setInputType(InputType.TYPE_NULL);
    }


    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view) {

        Button button = (Button) view;
        number.append(chekText(button));

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    private CharSequence chekText(Button button) {
        if (!button.getText().equals("."))
            if (number.getText().charAt(0) == '0' && number.getText().charAt(1) != '.') {
                number.setText("");
            }
        return button.getText();
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String _number = this.number.getText().toString();

        // если введенно что-нибудь
        if (_number.length() > 0) {
            _number = _number.replace(',', '.');
            try {
                performOperation(Double.valueOf(_number), op);
            } catch (NumberFormatException ex) {
                number.setText("");
            }
        }
        lastOperation = op;
        if (!(lastOperation.equals("=") || lastOperation.equals("C")))
            result.append(lastOperation);
    }

    private void performOperation(Double _number, String operation) {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = _number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            System.out.println("test");
            switch (lastOperation) {
                case "=":
                    operand = _number;
                    break;
                case "/":
                    if (_number == 0) {
                        result.setText("Недопустимая операция");
                    } else {
                        operand /= _number;
                    }
                    break;
                case "*":
                    operand *= _number;
                    break;
                case "+":
                    operand += _number;
                    break;
                case "-":
                    operand -= _number;
                    break;
                case "С":
                    this.number.setText("0");
                    result.setText("0");
                    return;
            }
        }
        result.setText(operand.toString().replace('.', ','));
        this.number.setText("0");
    }

}
