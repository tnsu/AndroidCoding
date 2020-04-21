package com.gmail.tnbinsu.tnt.blockgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
// 현재 화면이 가로/세로 변경되지 않도록 지정하기
// AndroidManifest.xml 에 screenOrientation="portrait" 지정

// 액션바 없애기 -> styles.xml 에서 NoActionBar 지정
public class Intro extends AppCompatActivity {
    // 초기화면
    // 3초동안보이고 다음화면 (Main) 으로 넘어가기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //메세지를 받기만하면 다음화면 넘어가게하기
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent); // 화면 전환
                finish(); //  intro 화면은 종료 적층구조이기때문에 화면을 종료해줘야 사라짐
            }
        };
        mHandler.sendEmptyMessageDelayed(1,3000); //3초뒤에 핸들러 메세지 실행됨

    }//end onCreate

}// end Act
