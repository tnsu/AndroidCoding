package com.lec.android.a016_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {

    private TextView tv;
    private SensorManager sm;

    // TYPE_ORTENRARION <-- 현재 de
    Sensor accelerimeter;
    Sensor magnetonmeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.textView1);

        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
      //  Sensor s = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        accelerimeter = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetonmeter = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


    }
    // 화면 동작하기 직전에 센서 자원 획득
    @Override
    protected void onResume() {
        super.onResume();
        //센서 값이 변경되었을때마다 콜백 받기 위한 리스너 등록 SensorEventListener 객체
        sm.registerListener((SensorEventListener)this,
                accelerimeter, // 콜백 원하는 센서
                SensorManager.SENSOR_DELAY_UI // 자연시간 2ms (최소 2ms
            );
        sm.registerListener((SensorEventListener)this,
                magnetonmeter,
                SensorManager.SENSOR_DELAY_UI);

    }

    //화면 빠져나가기 전에 센서 자원 반납
    @Override
    protected void onPause() {
        super.onPause();
        // 센서에 등록된 리스너 해제
        sm.unregisterListener(this); // 반납할 센서

    }

    float [] mGravity;
    float[] mGeomagnetic;

    //SensorEventListener 객체의 메소드들
    // 센서 값이 변경될때 마다 호출
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values; // 센서 값들은 fliat[] 로 넘어옴
        }
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            mGeomagnetic = event.values;
        }
        if(mGeomagnetic != null && mGravity != null){
            float[] R = new float[9];
            float[] I = new float[9];

            Boolean success= SensorManager.getRotationMatrix(R,I,mGravity, mGeomagnetic);

            if(success){
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);

                float azimuth = orientation[0]; // z 축 회전방향 (-3 ~ +3)
                float pitch = orientation[1]; // x축 회전방향 (+1.5 ~ -1.5)
                float roll = orientation[2]; // y축 회전방향 (+1.5 ~ -1.5)

                String str = String.format("%10s|%10s|%10s\n%10.2f|%10.2f|%10.2f", //%10.2f 전체 열자리 소수점두자리
                        "방위각","피치","롤",
                        azimuth, pitch, roll);

                tv.setText(str);
                Log.d("myapp",str);
            }

        }

        tv.setText("onSensorChanged");
        Log.d("myapp","onSensorChanged");
    }

    // 센서의 정확도가 변경되었을때 호출되는 콜백 // 정확도가 변할 땐 따로 처리하는 매소드 필요
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        tv.setText("onAccuracyChanged");
        Log.d("myapp","onAccuracyChanged");
    }
}
