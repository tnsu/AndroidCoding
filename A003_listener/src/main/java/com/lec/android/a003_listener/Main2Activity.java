package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
           int total;
            public MyNum(){};
           public MyNum(String num2){this.num2 = num2;}
           @Override
           public void onClick(View v) {
               String num = (String)((Button) v).getText();
               String str4 = etV.getText().toString();
               String tot = "";
               tot += num2;

               etV.setText(etV.getText().append(tot));
               etText.setText(etText.getText().append(num));
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
                           Log.d("dd",m[i]);
                       }
                   }
                    int in1 = Integer.parseInt(m[0]);
                    int in2 = Integer.parseInt(m[2]);
                   switch(m[1]){
                       case "+": total =in1 + in2; break;
                       case "-": total =in1 - in2; break;
                       case "/": total =in1 / in2; break;
                       case "*": total =in1 * in2; break;
//                       case "*": etText.setText(in1 * in2); break;
//                       case "*": total = in1 * in2; break;
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
//        class Operator implements View.OnClickListener{
//            String oper;
//            int total;
//            public Operator(String oper){this.oper = oper;}
//            @Override
//            public void onClick(View v) {
//                int num1 = Integer.parseInt(etText.getText().toString());
//                if(oper == "+"){
//                    total += num1 ;
//                    etText.setText(etText.getText().append(oper));
//                }
//                if(oper == "="){
//                    etText.setText(total + num1);
//                }
//                Log.d("myapp",  num1 +" 버튼이 클릭되었습니다.");
//                Log.d("myapp", total+" 입니다.");
////                switch (oper){
////                    case "+": total+= num1; break;
////                    case "-": total-= num1;
////                }
//
//            }
//        }
