package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
// 긴 음악을 재생할때 사용
public class Main2Activity extends AppCompatActivity {
    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnResume;
    private ImageView btnStop;
    SeekBar sb; // 음악 재생위치를 나타내는 시크바

    MediaPlayer mp; //음악 재생을 위한 객체

    int pos; // 재생위치
    boolean isTracking = false; // 드래그 중일때 시크바 못움직이게 만듬

    class MyThread extends Thread{

        @Override
        public void run() {
            // SeekBar 가 음악재생시, 움직이게 하기
            while(mp.isPlaying()){ // mp 가 현재 재생중이면
               pos = mp.getCurrentPosition(); // 현재 재생중인 위치 ms (int)
                if(!isTracking){
                    sb.setProgress(pos); // SeekBar 이동 --> onProgressChanged() 호출함함
               }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnStop = findViewById(R.id.btnStop);
        sb = findViewById(R.id.sb);

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);
//TODO
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // SB 값 변경될때 마다 호출
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               // 음악이 끝까지 연주 완료 되었다면
                if(seekBar.getMax() == progress && !fromUser){// 곡의 길이를 다돌았다면
                    btnPlay.setVisibility(View.VISIBLE);
                    btnPause.setVisibility(View.INVISIBLE);
                    btnResume.setVisibility(View.INVISIBLE);
                    btnStop.setVisibility(View.INVISIBLE);
                    if(mp != null) mp.stop();
                }
            }

            // 드래그 시작(트레킹) 하면 호출
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking = true;

            }
            // 드래그 마치면(트레킹 종료) 하면 호출
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pos = seekBar.getProgress(); // 시크바의 위치를 pos 넣음
                if(mp != null) mp.seekTo(pos); // 사용자가 지정한 위치로 이동

                isTracking = false;
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MediaPlayer 객체 초기화, 재생
                mp = MediaPlayer.create(
                        getApplicationContext(), // 현재 화면의 재어권자
                        R.raw.chacha // 음악파일 리소스
                        );
                mp.setLooping(false); // true : 무한반복

                // 재생이 끝나면 호출되는 콜백 리소스
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.d("myapp","연주종료 | 현재재생 위치 : " + mp.getCurrentPosition() +
                                " /전체곡의 크기 : " + mp.getDuration());

                        // getDuration() : 단위 ms
                        btnPlay.setVisibility(View.VISIBLE);
                        btnPause.setVisibility(View.INVISIBLE);
                        btnResume.setVisibility(View.INVISIBLE);
                        btnStop.setVisibility(View.INVISIBLE);
                    }
                });

                mp.start(); //노래 재생 시작

                int duration = mp.getDuration();// 음원의 재생시간 (ms)
                sb.setMax(duration);//SeekBar 의 범위를 음악의 재생시간으로 설정
                new MyThread().start();// SeekBar 쓰레드 시작

                btnPlay.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //음악 종료
                pos = 0;

                if(mp != null){
                    mp.stop(); // 재생멈춤
                    mp.seekTo(0); // 음악의 처음으로 이동
                    mp.release(); // 자원해제
                    mp = null;
                }

                sb.setProgress(0); // SeekBar도 초기 위치로

                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });

        //일시중지
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = mp.getCurrentPosition(); //현재 재생중이던 위치 선정
                mp.pause(); // 일시 중지
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });

        // 멈춘 시점부터 재시작
        // 일시중지에서 Thread 도 끝남 다시실행 해줘야함
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(pos); // 일시정지 위치로 이동
                mp.start(); // 재생 시작
                new MyThread().start(); // SeekBar 이동시작(쓰레드)
            }
        });
    }// end onCreate

    // MadiaPlayer는 자원이다.
    @Override
    protected void onPause() {
        super.onPause();
        if(mp != null){
            mp.release(); // 자원 해제
        }

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);
    }
}// end Act
