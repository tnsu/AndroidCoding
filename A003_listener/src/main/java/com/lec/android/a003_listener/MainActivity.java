package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvResult;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //onCreate()
        // 액티비티(화면 객체)가 생성될때 호출되는 매소드
        // 엑티비티 초기화 하는 코드 작성
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        //리소스 아이디값은 상수다.
        //변수이름과 상수이름이 같으면 편하다


        tvResult = findViewById(R.id.tvResult);
        et = findViewById(R.id.et);
        LinearLayout ll = findViewById(R.id.ll);
        // 한번 값이 정해지면 Local inner를 쓸수없음
        //  final LinearLayout ll = findViewById(R.id.ll); //사용하거나
        // 멤버변수에 선언후 사용하면 사용할수있음 (ll = findViewById(R.id.ll); )
             //Local inner 에서 사용할수 있는 것은 멤버변수, effective final !!

        //방법2 : 익명클래스(anonymous class) 사용
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 클릭 되었을때 호출되는 메소드(콜백 메소드 callback method)
                Log.d("myapp","btn2가 클릭되었습니다.");
                tvResult.setText("버튼2가 클릭됨");
                tvResult.setBackgroundColor(Color.YELLOW);
            }
        });

        //다양한 이벤트, 다양한 리스너 등록 가능
        btn2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) { //롱클릭 발생시 수행하는 콜백 메소드
                Log.d("myapp","버튼2가 롱클릭 되었습니다.");
                tvResult.setText("버튼2가 롱클릭 되었습니다.");
                tvResult.setBackgroundColor(Color.CYAN);
                //return false;  //false 리턴하면 이벤트가 click 까지 간다.
            return true; // true 리턴하면 이벤트가 Long click 으로 소멸(consume)된다.
            }
        });

        //방법3: Lambda - expression 사용하기
        //androidStudoio 의 세팅 필요!!
        btn3.setOnClickListener((v) ->{
            Log.d("myapp", "버튼3이 클릭되었다.");
            tvResult.setText("버튼3 클릭");
            ll.setBackgroundColor(Color.rgb(164,198,57));
        });

        // 방법4 : implement 한 클래스 사용
        // 동일한 동작을 수행하는 여러개의 객체가 있을때는 사용하기 좋음
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);

        class MyListener implements View.OnClickListener{

            String name;

            public MyListener(String name){this.name = name;}
            @Override
            public void onClick(View v) { // 다형성 view 최상위
                String tag = (String)v.getTag();
                String text = (String)((Button) v).getText(); // getText()는 CharSequence 객체 리턴
                String msg = String.format("%s 버튼 %s 이 클릭[%s]",name,text,tag);
                Log.d("myapp",msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name));
            }
        }

        btnA.setOnClickListener(new MyListener("안녕1"));
        btnB.setOnClickListener(new MyListener("안녕2"));
        btnC.setOnClickListener(new MyListener("안녕3"));
        btnD.setOnClickListener(new MyListener("안녕4"));
        btnE.setOnClickListener(new MyListener("안녕5"));
        btnF.setOnClickListener(new MyListener("안녕6"));

        // 방법5 : 액티비티가 implement
        Button btnClear = findViewById(R.id.binClear);
        btnClear.setOnClickListener(this);

        // 연습
        //+,- 버튼 누르면 tvResult의 글씨가 점점 커지고 작아지게 하기
        // getTextSize() : float 값 리턴
        Button btnInc = findViewById(R.id.btnInc);
        Button btnDec = findViewById(R.id.btnDec);
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size = tvResult.getTextSize();
                Log.d("myapp","글꼴사이즈 : " + size);
                tvResult.setTextSize(0,size + 5);

                // 이렇게 해도동작은함
                //  float size = tvResult.getTextSize()+1;
                //tvResult.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            }
        });

        btnDec.setOnClickListener((v) -> {
            float size = tvResult.getTextSize();
            Log.d("myapp","글꼴사이즈 : " + size);
            tvResult.setTextSize(0,size - 5);
//            float newSize = tvResult.getTextSize();
//            tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize);
        });

    }// end onCreate()
        // 방법1 : 레이아웃 XML 의 onXXX 속성으로 지정
        public void changeText(View v){
            //Log.d(tag,msg);
            //Log 창의 Debug 메세지로 출력
            Log.d("myapp","버튼1이 클릭되었습니다.");
            tvResult.setText("버튼1이 클릭되었습니다");
        }

    // 방법5 : 액티비티가 implement  한것을 사용하는 방법
    //public class MainActivity extends AppCompatActivity implements View.OnClickListener{}
    @Override
    public void onClick(View v) {
        Log.d("myapp","Clear 버튼이 클리되었습니다.");
        tvResult.setText("Clear버튼이 클릭되었습니다");
        tvResult.setBackgroundColor(Color.WHITE);

        et.setText("");
    }
}//end Activity
