package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPw, etNumber, etEmail;
    TextView tvName, tvPw, tvNumber, tvEmail,tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPw = findViewById(R.id.etPw);
        etNumber = findViewById(R.id.etNumber);
        etEmail = findViewById(R.id.etEmail);

        tvName = findViewById(R.id.tvName);
        tvPw = findViewById(R.id.tvPw);
        tvNumber = findViewById(R.id.tvNumber);
        tvEmail = findViewById(R.id.tvEmail);

        tvResult = findViewById(R.id.tvResult);

        // 포커스 변화
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            // hasFocus : true - 포커스 받은 경우  false -포커스 잃은 경우
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                }else {
                    // 투명색으로
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

        // 키보드가 눌릴때
        // 자판키보드로만 가능함
        etPw.setOnKeyListener(new View.OnKeyListener() {
            // keyCode : 눌린 키의 코드 값
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tvResult.setText( ((EditText)v).getText().toString());
                return false;
            }
        });

        // 값의 변화
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                tvResult.setText("입력완료 : " + actionId); //
                return false;
            }
        });
    }
}
