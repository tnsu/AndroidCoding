package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// 화면이 없는 액티비티 생성 가능
public class CalcActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1", 0);
        int num2 = intent.getIntExtra("num2", 0);

        intent.putExtra("plus", num1 + num2);
        intent.putExtra("minus", num1 - num2);

        // 호출한 화면에 값 되돌려 주기
        setResult(RESULT_OK,intent); // 안드로이드에 정의 된 상수 RESULT_OK


        finish(); // onDestory()와 동일

    }// end onCreate
}// end Activity
