package com.example.againcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView, result;

    Button clear, bracketRight, bracketLeft, percentage, divide, add, subtract, multiply, equal;

    Button one, two, three, four, five, six, seven, eight, nine, point, zero, delete ;

    Boolean clearResult = true;
    Boolean doubleMathActionBug = true;
    Boolean doubleDotBug = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //code for hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView1);
        result = (TextView)findViewById(R.id.textView2);

        clear = (Button)findViewById(R.id.buttonClearText);
        bracketRight = (Button)findViewById(R.id.buttonBracketRight);
        bracketLeft = (Button)findViewById(R.id.buttonBracketLeft);
        percentage = (Button)findViewById(R.id.buttonPercentage);
        divide = (Button)findViewById(R.id.buttonDivide);
        add = (Button)findViewById(R.id.buttonAdd);
        subtract = (Button)findViewById(R.id.buttonSubtraction);
        multiply = (Button)findViewById(R.id.buttonMultiply);
        equal = (Button)findViewById(R.id.buttonEqual);
        delete = (Button)findViewById(R.id.buttonDelete);


        one = (Button)findViewById(R.id.button1);
        two = (Button)findViewById(R.id.button2);
        three = (Button)findViewById(R.id.button3);
        four = (Button)findViewById(R.id.button4);
        five = (Button)findViewById(R.id.button5);
        six = (Button)findViewById(R.id.button6);
        seven = (Button)findViewById(R.id.button7);
        eight = (Button)findViewById(R.id.button8);
        nine = (Button)findViewById(R.id.button9);
        point = (Button)findViewById(R.id.buttonPoint);
        zero = (Button)findViewById(R.id.buttonZero);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView.setText(null);
                result.setText(null);
                doubleMathActionBug = false;
                doubleDotBug = true;
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String backspace = null;
                if (textView.getText().length()>0){
                    StringBuilder strB = new StringBuilder(textView.getText());
                    strB.deleteCharAt(textView.getText().length()-1);
                    backspace = strB.toString();
                    textView.setText(backspace);

                }
            }
        });


        bracketRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + ")");


            }
        });

        bracketLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "(");
                doubleMathActionBug = false;


            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "%");

            }
        });


        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                if (!doubleMathActionBug){
                    textView.setText(textView.getText() + "");}
                else {
                    textView.setText(textView.getText() + "/");
                    doubleMathActionBug = false;
                    doubleDotBug = true;
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);
                if (!doubleMathActionBug){
                    textView.setText(textView.getText() + "");}
                else {
                    textView.setText(textView.getText() + "+");
                    doubleMathActionBug = false;
                    doubleDotBug = true;
                }

            }
        });


        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);
                if (!doubleMathActionBug){
                    textView.setText(textView.getText() + "");}
                else {
                    textView.setText(textView.getText() + "-");
                    doubleMathActionBug = false;
                    doubleDotBug = true;
                }


            }
        });


        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);
                if (!doubleMathActionBug){
                    textView.setText(textView.getText() + "");}
                else {
                    textView.setText(textView.getText() + "*");
                    doubleMathActionBug = false;
                    doubleDotBug = true;
                }

            }
        });


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orgString = textView.getText().toString();

                result.setText(findValueInBraces(orgString));
                textView.setText(null);
                clearResult = false;
                doubleMathActionBug = false;
                doubleDotBug = true;
            }

            public String findValueInBraces(String finalStr) {

                while (finalStr.contains("(") && finalStr.contains(")")) {
                    int fIndex = finalStr.indexOf("(");
                    int nIndex = finalStr.indexOf(")");
                    String subString = finalStr.substring(fIndex + 1, nIndex);
                    finalStr = finalStr.substring(0, fIndex)
                            + calculate(subString)
                            + finalStr.substring(nIndex + 1,
                            finalStr.length());
                }
                return calculate(finalStr);

            }

            public String calculate(String finalString) {

                while (finalString.contains("(") && finalString.contains(")")) {
                    findValueInBraces(finalString);
                }
                while (!isNum(finalString)) {
                    List<Integer> positions = getOperandPosition(finalString);
                    int pos = positions.get(0);
                    if (positions.size() >= 2 && positions.get(1) != null) {
                        int nxtPos = positions.get(1);
                        finalString = getValue(finalString.substring(0, nxtPos), pos)
                                + finalString.substring(nxtPos, finalString.length());
                    } else {
                        finalString = getValue(
                                finalString.substring(0, finalString.length()), pos);
                    }
                }
                return finalString;

            }

            public boolean isNum(String str) {
                if (str.contains("+") || str.contains("-") || str.contains("*")
                        || str.contains("/") || str.contains("%")) {
                    return false;
                }
                return true;
            }

            public List<Integer> getOperandPosition(String str) {

                List<Integer> integers = new ArrayList<Integer>();

                if (str.contains("+")) {
                    integers.add(str.indexOf("+"));
                }

                if (str.contains("-")) {
                    integers.add(str.indexOf("-"));
                }

                if (str.contains("*")) {
                    integers.add(str.indexOf("*"));
                }

                if (str.contains("/")) {
                    integers.add(str.indexOf("/"));
                }
                if (str.contains("%")) {
                    integers.add(str.indexOf("%"));
                }

                Collections.sort(integers);
                return integers;
            }

            public String getValue(String str, int pos) {
                double finalVal = 0;
                double a = Double.parseDouble(str.substring(0, pos));
                double b = Double.parseDouble(str.substring(pos + 1, str.length()));
                char c = str.charAt(pos);

                if (c == '*') {
                    finalVal = a * b;
                } else if (c == '/') {
                    finalVal = a / b;
                } else if (c == '+') {
                    finalVal = a + b;
                } else if (c == '-') {
                    finalVal = a - b;

                }else if (c == '%') {
                    pos --;
                    if (c == '*') {
                        finalVal = a/100*b;
                    } else if (c == '+') {
                        finalVal = a + a/100*b;
                    } else if (c == '-') {
                        finalVal = a - a/100*b;

                    }

                    }
                return String.valueOf(finalVal);
            }


        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "1");
                doubleMathActionBug = true;

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "2");
                doubleMathActionBug = true;

            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "3");
                doubleMathActionBug = true;

            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "4");
                doubleMathActionBug = true;

            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "5");
                doubleMathActionBug = true;

            }
        });


        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "6");
                doubleMathActionBug = true;

            }
        });


        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "7");
                doubleMathActionBug = true;

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "8");
                doubleMathActionBug = true;

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "9");
                doubleMathActionBug = true;

            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);
                if (doubleDotBug){
                    textView.setText(textView.getText() + ".");
                    doubleMathActionBug = false;
                    doubleDotBug = false;
                }else {
                    textView.setText(textView.getText() + "");
                }



            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clearResult)result.setText(null);

                textView.setText(textView.getText() + "0");
                doubleMathActionBug = true;

            }
        });
    }

}