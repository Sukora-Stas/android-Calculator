package com.androiddev.bsuir.android_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    }


    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view) {

        Button button = (Button) view;
        number.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = this.number.getText().toString();

    }

}
