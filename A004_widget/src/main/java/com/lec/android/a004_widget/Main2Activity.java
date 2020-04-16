package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button btnplus, btnMins, btnMul, btnDiv;
    EditText op1, op2;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        btnplus = findViewById(R.id.btnPlus);
        btnMins = findViewById(R.id.btnMinus);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);



        btnplus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str1 = op1.getText().toString().trim();
                String str2 = op2.getText().toString().trim();
                int in1 = Integer.parseInt(str1) + Integer.parseInt(str2);
                String result = String.format("%d",in1);
                tvResult.setText(result);
                Log.d("myspp",in1 + "입니다");
            }
        });




    }
}
