package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText etText;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
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
        btnClear = findViewById(R.id.btnClear);
        btnDiv = findViewById(R.id.btnDiv);
        btnDif = findViewById(R.id.btnDif);
        btnSum = findViewById(R.id.btnSum);
        btnEq = findViewById(R.id.btnEq);
        btnMul = findViewById(R.id.btnMul);

        etText = findViewById(R.id.etText);

       class MyNum implements View.OnClickListener{

           String num;

           public MyNum(String num){this.num = num;}
           @Override
           public void onClick(View v) {
                String num = (String)((Button) v).getText();
           }
       }


    }
}
