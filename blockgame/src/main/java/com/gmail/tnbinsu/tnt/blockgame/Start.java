package com.gmail.tnbinsu.tnt.blockgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class Start extends AppCompatActivity implements View.OnClickListener {

    TextView tvTime; //시간표시
    TextView tvPoint; //점수표시

    int time = 30; // 주어지는 시간값
    int point = 0; // 시작 점수값

    // 블럭이미지 리소스 배열
    int[] img = {R.drawable.block_red, R.drawable.block_green, R.drawable.block_blue};

    // 떨어지는 블럭의 ImageView 배열 객체 // iv[0] <-- null 로 초기화되있음
    ImageView[] iv = new ImageView[8];

    private Vibrator vibrator;  // 진동
    private SoundPool soundPool;  // 음향,

    private int soundID_OK; // 음향 id: 블럭에 맞추었을 때
    private int soundID_Error; // 음향 id : 블럭 못 맞췄을 때
    // 배경음악 재생
    private MediaPlayer mp;

    // 다이얼로그 ID
    final int DIALOG_TIMEOVER = 1;

    // 시간
    Handler handler = new Handler();

    // 게임진행 쓰레드
    class GameThread extends Thread{
        @Override
        public void run() {
            // 시간을 1 초마다 다시 표시(업데이트)
            // handler 사용하여 화면 UI 업데이트

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(time >=0){
                        tvTime.setText("시간: " + time);

                        if(time > 0){
                            time--; // 1 감소하고 , 1초후에 다시 run()수행
                            // 자기자신을 호출해 다시 돔
                            handler.postDelayed(this,1000);
                        }else{
                            // time => 0이 된경우
                            AlertDialog.Builder builder =
                                    new AlertDialog.Builder(Start.this);
                            builder.setTitle("Time OUT")
                                    .setMessage("점수 : " + point)
                                    .setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish(); // 현재화면 종료. 메인화면으로 가기
                                        }
                                    })
                                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 게임 리셋하고 새 게임 시작
                                            time = 25;
                                            point = 0;
                                            tvTime.setText("시간 : " + time);
                                            tvPoint.setText("점수 : "+ point);
                                            new GameThread().start(); // 새로운 게임 시작
                                        }
                                    })
                                    .setCancelable(false) // 바깥여백 눌러도 창꺼지지 않음
                                    ;
                            builder.show();
                        }//end if
                    }// end if
                }//end run()
            },1000); // 1초후에 시간표시시
            //Rinnable


        }// end run
    }// end GameThread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상태바(status bar) 없에기, 반드시 setContentView() 앞에서 처리
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);

        // 레이아웃 객체(뷰) 초기화
        tvTime = findViewById(R.id.tvTime);
        tvPoint = findViewById(R.id.tvPoint);

        ImageView ivRed = findViewById(R.id.ivRed);
        ImageView ivGreen = findViewById(R.id.ivGreen);
        ImageView ivBlue = findViewById(R.id.ivBlue);

        // 배열에 이미지 담기
        iv[0] = findViewById(R.id.ivBlock1);
        iv[1] = findViewById(R.id.ivBlock2);
        iv[2] = findViewById(R.id.ivBlock3);
        iv[3] = findViewById(R.id.ivBlock4);
        iv[4] = findViewById(R.id.ivBlock5);
        iv[5] = findViewById(R.id.ivBlock6);
        iv[6] = findViewById(R.id.ivBlock7);
        iv[7] = findViewById(R.id.ivBlock8);

        // 게임이 시작되면 초기화 랜덤으로 블럭의 색상 지정
        for(int i = 0; i < iv.length; i++){
            // 0,1,2 각각 red, green, blue
            int num = new Random().nextInt(3); // 0,1,2 중의 랜덤정수
            iv[i].setImageResource(img[num]); //
            iv[i].setTag(num+""); // 버튼의 색상을 판단하기위힌 값으로 활용
        }

        // 점수 초기화
        point = 0;
        tvPoint.setText("점수 : "+point);

        //r,g,b 버튼의 이벤트 리스너 등록
        ivRed.setOnClickListener(this);
        ivGreen.setOnClickListener(this);
        ivBlue.setOnClickListener(this);

        // 시긴표시 게임진행 쓰레드 시작하기
        new GameThread().start();

    }// end onCreate

    @Override
    public void onClick(View v) {
        // 버튼을 눌렀을때 호출되는 콜백
        // 클릭과 같은 색깔의 보든이 눌렸는지 판별, 같은 블럭이면 이미지 블럭 한칸씩 내려오기, 맨위에는 새로운 블럭생성

        boolean isOk = false; // 맞추었는지 판별결과

        ImageView imageView = (ImageView) v;

        switch(imageView.getId()){ // 리소스 아이디
            // 맨 아래 블럭 ImageView 의 색상과 일치하는 버튼인지 판정
            case R.id.ivRed: // 0
                if("0".equals(iv[7].getTag().toString())) isOk = true; //빨강블럭의 tag값 "0" // getTag의 글자가
                break;
            case R.id.ivGreen:
                if("1".equals(iv[7].getTag().toString())) isOk = true; //초록블럭의 tag값 "1"
                break;
            case R.id.ivBlue:
                if("2".equals(iv[7].getTag().toString())) isOk = true; //파랑블럭의 tag값 "2"
                break;
        }// end switch
        if(isOk){// 버튼 색상을 맞추었다면

            // 위의 7개 블럭을 한칸 아래로 이동 iv[i] -> iv[i+1]
            for(int i = iv.length-2; i >= 0;i--){
                int num = Integer.parseInt(iv[i].getTag().toString()); // 0,1,2
                iv[i+1].setImageResource(img[num]); // i 아래쪽 블럭 이미지 업데이트
                iv[i+1].setTag(num+""); // i 아래쪽 블럭 tag값 업데이트
            }// end for 6

            // 가장 위의 블럭(iv[0]) ImageView 는 랜덤으로 생성
            int num = new Random().nextInt(3);
            iv[0].setImageResource(img[num]);
            iv[0].setTag(num+"");

            //진동 & 음향
            vibrator.vibrate(200);
            soundPool.play(soundID_OK,1,1,0,0,1);
            //점수 올리기
            point++;
            tvPoint.setText("점수 : "+ point);
        }else{ // 버튼과 색깔이 틀리면

            //진동 & 음향
            vibrator.vibrate(new long[]{20,80,20,80,20,80},-1);
            soundPool.play(soundID_Error,1,1,0,0,1);
            //점수 깍기
            point--;
            tvPoint.setText("점수 : "+ point);

        }


    }// end onClick

    @Override
    protected void onResume() { // 사용자와 상호작용을 할수 있을때
        super.onResume();
        //자원 획득

        //Vibrator 객체 얻어오기
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        //SoundPool 객체
        soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        soundID_OK = soundPool.load(Start.this, R.raw.gun3,1);
        soundID_OK = soundPool.load(Start.this, R.raw.error,1);

        //MediaPlayer 객체
        mp = MediaPlayer.create(getApplicationContext(),R.raw.bgm);
        mp.setLooping(false); // 반복재생 안함
        mp.start(); // 배경음악 재생 시작
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 자원 해제

        if(mp != null){
            mp.stop();
            mp.release();
        }
    }
}// end Act







