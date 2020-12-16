package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

// test for android stdio git 2
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button zero, one, two, three, four, five, six, seven, eight, nine, point;
    Button clear, add, subtraction, multiplication, division, root, surplus, equal, delete;
    TextView theFirstView, theOptionView, theSecondView, theAnswerView ;
    AlertDialog.Builder alert;
    int answerFlag = 0;
    double number1, number2;
    String option = "", str1 = "", str2 = "", result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                if(answerFlag == 0){
                    if(option.equals("")){
                        str1 = str1+((Button) view).getText().toString();
                        theFirstView.setText(str1);
                    }
                    else{
                        str2 = str2+((Button) view).getText().toString();
                        theSecondView.setText(str2);
                    }
                }

                if(answerFlag == 1){
                    clear();
                    answerFlag = 0;
                    str1 = str1+((Button) view).getText().toString();
                    theFirstView.setText(str1);
                }

                break;

            case R.id.point:
                if(!str1.equals("") && option.equals("")){
                    str1 = str1+((Button) view).getText().toString();
                    theFirstView.setText(str1);
                }
                if(!option.equals("") && !str2.equals("")){
                    str2 = str2+((Button) view).getText().toString();
                    theSecondView.setText(str2);
                }
                else{
                    //什么都不执行
                }
                break;

            case R.id.add:
                if (!str1.equals("")) {
                    option = "+";
                    theOptionView.setText("+");
                }
                break;

            case R.id.subtraction:
                if (!str1.equals("")) {
                    option = "-";
                    theOptionView.setText("-");
                }
                break;

            case R.id.multipliction:
                if (!str1.equals("")) {
                    option = "×";
                    theOptionView.setText("×");
                }
                break;

            case R.id.division:
                if (!str1.equals("")) {
                    option = "÷";
                    theOptionView.setText("÷");
                }
                break;

            case R.id.surplus:
                if (!str1.equals("")) {
                    option = "%";
                    theOptionView.setText("%");
                }
                break;

            case R.id.root:
                if (str1.equals("")) {
                    option = "√";
                    theOptionView.setText("√");
                }
                break;

            case R.id.clear:
                clear();
                break;

            case R.id.delete:
                // 已输入第二个数
                if(theAnswerView.getText().toString().equals("")) {
                    if(!theSecondView.getText().toString().equals("")) {
                        int length = str2.length();
                        if(length == 1){
                            theSecondView.setText("");
                        }
                        else{
                            str2 = str2.substring(0,length-1);
                            theSecondView.setText(str2);
                        }
                        break;
                    }
                    // 未输入第二个数，已输入运算符
                    if(theSecondView.getText().toString().equals("") && !theOptionView.getText().toString().equals("")){
                        theOptionView.setText("");
                        break;
                    }
                    // 未输入第二个数和运算符，已输入第一个数
                    if(theSecondView.getText().toString().equals("") && theAnswerView.getText().toString().equals("") && (theFirstView.getText().toString()) != ""){
                        int length = str1.length();
                        if(length == 1){
                            theFirstView.setText("");
                        }
                        else{
                            str1 = str1.substring(0,length-1);
                            theFirstView.setText(str1);
                        }
                        break;
                    }
                }
                break;


            case R.id.equal:
                answerFlag = 1;
                // 只输入一个数按“=”，直接输出这个数
                if (option.equals("") && theSecondView.getText().toString().equals("")) {
                    result = theFirstView.getText().toString();
                }
                // 只输入第一个数和运算符，直接跳过
                else if (theSecondView.getText().toString().equals("")) {
                    break;
                }
                // 开方运算
                else if (option.equals("√")) {
                    number2 = Double.parseDouble(theSecondView.getText().toString());
                    theAnswerView.setText(isInteger(Math.sqrt(number2)));
                }
                // 完整输入了两个数和运算符
                else{
                    number1 = Double.parseDouble(theFirstView.getText().toString());
                    number2 = Double.parseDouble(theSecondView.getText().toString());

                    if (option.equals("+")) {
                        result = isInteger(number1 + number2);
                    }
                    if (option.equals("-")) {
                        result = isInteger(number1 - number2);
                    }
                    if (option.equals("×")) {
                        result = isInteger(number1 * number2);
                    }
                    if (option.equals("÷")) {
                        if (number2 == 0) {
                            alert.setTitle("警告")
                                    .setMessage("除数不能为0!")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                            break;
                        } else {
                            result = isInteger(number1 / number2);
                        }
                    }
                    if (option.equals("%")) {
                        result = isInteger(number1 % number2);
                    }
                    theAnswerView.setText("=" + result);
                    break;
                }
        }
    }

    public void initView() {
        zero = (Button)findViewById(R.id.zero);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        point = (Button)findViewById(R.id.point);
        clear = (Button)findViewById(R.id.clear);
        add = (Button)findViewById(R.id.add);
        subtraction = (Button)findViewById(R.id.subtraction);
        multiplication = (Button)findViewById(R.id.multipliction);
        division = (Button)findViewById(R.id.division);
        root = (Button)findViewById(R.id.root);
        surplus = (Button)findViewById(R.id.surplus);
        equal = (Button)findViewById(R.id.equal);
        delete = (Button)findViewById(R.id.delete);
        theFirstView = (TextView)findViewById(R.id.the_first_number);
        theOptionView = (TextView)findViewById(R.id.the_option);
        theSecondView = (TextView)findViewById(R.id.the_second_number);
        theAnswerView = (TextView)findViewById(R.id.the_answer);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        point.setOnClickListener(this);
        clear.setOnClickListener(this);
        add.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        root.setOnClickListener(this);
        surplus.setOnClickListener(this);
        equal.setOnClickListener(this);
        delete.setOnClickListener(this);
        alert = new AlertDialog.Builder(MainActivity.this);
    }

    public void clear(){
        str1 = "";
        str2 = "";
        theFirstView.setText("");
        theSecondView.setText("");
        theOptionView.setText("");
        theAnswerView.setText("");
        option = "";
    }

    public String isInteger(double n) {
        if (n % 1 == 0){
            return (int)n + "";
        }
        else {
            return n + "";
        }
    }
}