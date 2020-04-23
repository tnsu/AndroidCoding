package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

// #1 WebView 사용하여 웹 페이지 보여주기 // 내 앱에서 보는것, 특정
// #2 묵시적 Intent 사용하여 웹 브라우져 띄우기 //URL을 안드로이드 안에 설치되어있는 기본 액티비티가 뜨게 하는
public class Main2Activity extends AppCompatActivity {

    WebView wv;
    EditText etUrl;
    Button btnWebView, btnBrowser;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etUrl = findViewById(R.id.etUrl);
        wv = findViewById(R.id.wv);
        btnWebView = findViewById(R.id.btnWebView);
        btnBrowser = findViewById(R.id.btnBrowser);

        // WebView 세팅
        wv.getSettings().setJavaScriptEnabled(true);// 웹뷰의 세팅객체가 넘어옴 // JavaScript 사용여부 : 디폴트 false

        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString().trim();
                wv.loadUrl(url); // 읽어올  // 웹페이지로 url 읽어오기
                wv.setWebChromeClient(new WebChromeClient()); // 안하면 alert() 같은 알림창 안뜸
                wv.setWebViewClient(new WebViewClient()); // 안하면 html 내부에서 다른 페이지로 이동을 할 수가 없다.
            }
        });

        // 브라우저 가동 묵시적 intent tkdyd
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url) ); // 들어가는 형태에 따라 앱이 다르게 나옴Uri.parse(url)
                startActivity(intent);
            }
        });

    }// end onCreate()

    // 이전화면 구현  키보드로
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }









}// end Act
