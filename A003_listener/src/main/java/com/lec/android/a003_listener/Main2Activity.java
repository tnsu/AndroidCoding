package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.StringTokenizer;

public class Main2Activity extends AppCompatActivity {
    EditText etText, etV;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
            btnClear, btnDiv, btnDif, btnSum, btnEq, btnMul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnClear = findViewById(R.id.btnClear);
        btnDiv = findViewById(R.id.btnDiv);
        btnDif = findViewById(R.id.btnDif);
        btnSum = findViewById(R.id.btnSum);
        btnEq = findViewById(R.id.btnEq);
        btnMul = findViewById(R.id.btnMul);
        etV = findViewById(R.id.etV);

        etText = findViewById(R.id.etText);

       class MyNum implements View.OnClickListener{
           String num2;
           float total;
            public MyNum(){};
           public MyNum(String num2){this.num2 = num2;}
           @Override
           public void onClick(View v) {
               String num = (String)((Button) v).getText();
               String str4 = etV.getText().toString();
               String tot = "";
               tot += num2;

               etV.setText(etV.getText().append(tot)); // 보이지 않는 값 (/*+-)
               etText.setText(etText.getText().append(num)); //화면에 보여지는 값
               Log.d("myapp",  num+" 버튼이 클릭되었습니다.");
               Log.d("myapp",  str4+" 입니다.");
               if(num2 == "=") {
                   StringTokenizer token = new StringTokenizer(str4, "+-/*", true);
                       int cnt = token.countTokens();
                       String[] m = new String[cnt];
                   while (token.hasMoreTokens()) {
                       for (int i = 0;i<m.length;i++) {
                           tot = token.nextToken();
                           m[i] = tot;
                           Log.d("dd",m[i]); // 값이 제대로 나오고 있나 확인
                       }
                   }
/*
// 1. 두개의 피연산자와 하나의 연산자로만 계산가능

                    float in1 = Float.parseFloat(m[0]);
                    float in2 = Float.parseFloat(m[2]);
                   switch(m[1]){
                       case "+": total =in1 + in2; break;
                       case "-": total =in1 - in2; break;
                       case "/": total =in1 / in2; break;
                       case "*": total =in1 * in2; break;
//                       case "*": etText.setText(in1 * in2); break;
//                       case "*": total = in1 * in2; break;
                   }
*/

//2. 여러개의 연산이 가능하긴하나 순서대로 계산해 *,/ 연산이 먼저 계산되지 않음
// 연산의 합이 -가 나오기도 하지만 음수의 계산이 되지 않음
                   for (int i = 0; i< m.length; i++) {
                      // if(m[i] == "+"||m[i] == "-"||m[i] == "/"||m[i] == "*"){
                           switch (m[i]) {
                               case "+":
                                   m[i+1] = String.valueOf(Float.parseFloat(m[i - 1]) + Float.parseFloat(m[i + 1]));
                                   break;
                               case "-":
                                   m[i+1] = String.valueOf(Float.parseFloat(m[i - 1]) - Float.parseFloat(m[i + 1]));
                                   break;
                               case "/":
                                   m[i+1] = String.valueOf(Float.parseFloat(m[i - 1]) / Float.parseFloat(m[i + 1]));
                                   break;
                               case "*":
                                   m[i+1] = String.valueOf(Float.parseFloat(m[i - 1]) * Float.parseFloat(m[i + 1]));
                                   break;
                           }
                       total = Float.parseFloat(m[m.length-1]);
                   }
                 etText.setText(total+"");
                   etV.setText(total+"");
               }
           }
       }

       btn1.setOnClickListener(new MyNum("1"));
       btn2.setOnClickListener(new MyNum("2"));
       btn3.setOnClickListener(new MyNum("3"));
       btn4.setOnClickListener(new MyNum("4"));
       btn5.setOnClickListener(new MyNum("5"));
       btn6.setOnClickListener(new MyNum("6"));
       btn7.setOnClickListener(new MyNum("7"));
       btn8.setOnClickListener(new MyNum("8"));
       btn9.setOnClickListener(new MyNum("9"));
       btn0.setOnClickListener(new MyNum("0"));
       btnDif.setOnClickListener(new MyNum("-"));
       btnDiv.setOnClickListener(new MyNum("/"));
       btnMul.setOnClickListener(new MyNum("*"));
       btnSum.setOnClickListener(new MyNum("+"));
       btnEq.setOnClickListener(new MyNum("="));

       btnClear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("myapp","Clear 버튼이 클릭되었습니다.");
               etText.setText("");
               etV.setText("");
           }
       });
    }
}

