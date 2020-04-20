package com.lec.android.a011_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/** Handler
 *  자바는 자바가상머신 위에서 자체적으로 스레드를 생성하고 운영하긴 하지만,
 *  스레드 부분 만큼은 '운영체제'의 영향을 받는다.
 *  안드로이드에서 돌아가는 자바는 결국 '안드로이드 운영체제'의 영향을 받을수 밖에 없는데, ..
 *  안드로이드 운영체제의 경우 '작업스레드' 가 '메인스레드'의 변수를 참조하거나 변경을 할수 있어도,
 *  '메인스레드' 에서 정의된 UI 를 변경할수는 없게 하고 있습니다.  --> CalledFromWrongThreadException !! (이전 예제 참조)
 *
 *  안드로이드에서 '작업 스레드' 가 '메인스레드의 UI' 에 접근(변경/사용) 하려면 Handler 를 사용해야 합니다
 *  Handler 는 메인스레드와 작업스레드 간에 통신을 할 수 있는 방법입니다ㅣ
 *
 *  사용 방법:
 *      ▫ 'Handler 를 생성'한 스레드만이 다른 작업스레드가 전송하는 'Message' 나 'Runnable객체' 를 수신하는 기능을 할 수 있다.
 *      ▫  Message 전송은 sendMessage()
 *      ▫  Runnable 전송은 postXXX()
 */


public class Main2Activity extends AppCompatActivity {

    int mainValue = 0;
    int backValue1 = 0;
    int backValue2 = 0;
    TextView tvMainValue;
    TextView tvBackValue1, tvBackValue2, tvBackValue3, tvBackValue4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvMainValue = findViewById(R.id.tvMainValue);
        tvBackValue1 = findViewById(R.id.tvBackValue1);
        tvBackValue2 = findViewById(R.id.tvBackValue2);
        tvBackValue3 = findViewById(R.id.tvBackValue3);
        tvBackValue4 = findViewById(R.id.tvBackValue4);


    }// end onCreate()
}// end Act
