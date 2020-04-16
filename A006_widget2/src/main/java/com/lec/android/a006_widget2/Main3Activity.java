package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    TextView tvResult;
    SeekBar seekbar;

    int value = 0;
    int add = 2;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        seekbar = findViewById(R.id.seekBar1);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // 값의 변화가 생겼을 때 콜백
            //progress : 진행값 0 ~ 200
            // formUser : 사용자에 의한 진행값 변화인 경우 true
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvResult.setText("onProgressChanged: " + progress + "(" + fromUser + ")");
            }

            //Tracking 시작될때
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Tracking 시작", Toast.LENGTH_SHORT).show();
            }
            //Tracking 끝날때
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Tracking 종료", Toast.LENGTH_SHORT).show();
            }
        });

        // 앱 시작시 Thread .. Seekbar 증가 시키기
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = seekbar.getMax();

                while (true){
                    value = seekbar.getProgress() + add;
                    if(value > max || value < 0){
                        add = -add;
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            seekbar.setProgress(value);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }// end onCreate
}// end act
