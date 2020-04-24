package com.lec.android.a017_location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/** 구글맵 v2.0 서비스 사용하기
 *  1. Play Service 라이브러리 추가
 *  2. 메니페스트에 권한, 각종 설정추가 :
 *  3. 구글맵 API key 발급 받아 메니페스트에 추가
 *  4. XML 에 MapFragment 추가  <-- 지도표시용 Fragment
 *     SupportMapFragment 객체로 사용
 *  5. GoogleMap 객체를 사용하여 지도 조작
 *
 */


public class Main4Activity extends AppCompatActivity {

    //GoolgleMap 2.0 사용
    GoogleMap map;
    SupportMapFragment mapFragment;
    MarkerOptions myLocationMarker; //마커 (오버레이 객체)

    Button btnMap;
    Button btnMarker;
    EditText etLatitude;
    EditText etLongitude;
    EditText etMarker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btnMap = findViewById(R.id.btnMap);
        btnMarker = findViewById(R.id.btnMarker);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        etMarker = findViewById(R.id.etMarker);

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            // 지도가 준비되면 호출되는 콜백
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("myapp","지도 준비됨");
                map = googleMap;
            }
        });

        MapsInitializer.initialize(this);

        //버튼 누르면 입력된 좌표로 GoogleMap 이동
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        // 입력된 좌표 위에 마커 생성
        btnMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 위도 경도 위치를 받아옴
                double lat = Double.parseDouble(etLatitude.getText().toString());
                double lng = Double.parseDouble(etLongitude.getText().toString());

                // 구글맵에 좌표를 담는 객체
                LatLng curPoint = new LatLng(lat, lng);

                MarkerOptions markerOptions= new MarkerOptions()
                        .position(curPoint)  // 마커의 위치
                        .title(etMarker.getText().toString().trim()+"\n") // 마커의 제목(이름)
                        .snippet("● " + String.format("%.3f,%.3f", lat, lng)); // 마커의 메모들

                map.addMarker(markerOptions); // addMarker 하면 빨간게 찍힘
            }
        });

    }// end onCreate

    public void startLocationService(){
        // 위도 경도 위치를 받아옴
        double lat = Double.parseDouble(etLatitude.getText().toString());
        double lng = Double.parseDouble(etLongitude.getText().toString());

        //위도 경도를 담는 구글맵에 좌표를 담는 객체
        LatLng curPoint = new LatLng(lat, lng);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15)); // 어느 위치로 얼만큼 줌을 주겠다.

    }


}// end act
