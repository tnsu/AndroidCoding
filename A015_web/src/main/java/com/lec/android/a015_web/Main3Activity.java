package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
■서울시 지하철 역사 정보

        http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

        샘플url

        XML 버젼
        http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/xml/stationInfo/1/5/서울 utf-8

        JSON 버젼
        http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/json/stationInfo/1/5/서울
*/
public class Main3Activity extends AppCompatActivity {

    Button btnXML, btnJSON,btnParse ;
    EditText etText;
    static TextView tvResult;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    btnXML = findViewById(R.id.btnXML);
    btnJSON = findViewById(R.id.btnJSON);
    btnParse = findViewById(R.id.btnParse);
    etText = findViewById(R.id.editText);
    tvResult = findViewById(R.id.tvResult);
   final String url_add ="http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/json/stationInfo/1/5/";

    btnXML.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    btnJSON.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                final String url = url_add + etText.getText().toString().trim();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    request(url);
                    Log.d("myapp","request 요청됨");
                }
            }).start();

        }
    });
    btnParse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                parseJSON(tvResult.getText().toString());
                tvResult.setText("");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });

    }//end onCreate
    public void request(String urlStr){
        final StringBuilder sb = new StringBuilder();

        BufferedReader reader = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlStr);
            Log.d("myapp", " url : "+url);
            conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(5000); // Timeout 시간 설정. 경과하면 SocketTimeoutException 발생. 커넥트가 수립되는 시간
            Log.d("myapp", " url : "+conn);
                conn.setUseCaches(false); // 캐시 사용 안함
                conn.setRequestMethod("GET"); // GET 방식 request

                conn.setDoInput(true);
                int responseCode = conn.getResponseCode(); // getResponseCode code 값. 성공하면 200
                Log.d("myapp", " url : " + responseCode);
                if(responseCode == HttpURLConnection.HTTP_OK){ // 200 HTTP_OK
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while (true){
                        line = reader.readLine();
                        if(line == null) break;
                        sb.append(line+"\n");
                    }//end while

                }// end if

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(reader != null) reader.close();
                if(conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvResult.setText("" + sb.toString()); //sb 를 여기서 못써서 final 넣어줌

            }
        });

    }// end request()

/*
    public static String buildUrlAddress(String statnNm){
        String url_address = String.format("http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/json/stationInfo/1/5/%s",
                URLEncoder.encode(statnNm, "utf-8"));
        return url_address;

    }
*/
        public static void parseJSON(String jsonText) throws JSONException {
            JSONObject jodj = new JSONObject(jsonText);
            final JSONArray row = jodj.getJSONArray("stationList");
            Handler handler2 = new Handler();
            for (int i = 0; i < row.length(); i++) {
                JSONObject station = row.getJSONObject(i);

                final String subwayId = station.getString("subwayId");
               final String subwayNm = station.getString("subwayNm");

                 handler2.post(new Runnable() {
                     @Override
                     public void run() {
                         tvResult.append("---------\nid"+ subwayId+"\nname: " + subwayNm +"\n");
                     }
                 });

            }
    }// parseJSON



}//end Main3Activity
