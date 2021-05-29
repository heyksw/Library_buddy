package com.inha.longstone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {

    private GoogleMap mMap;
    private String jsonString;
    ArrayList<Univlist> univArrayList;
    private int univinSeoul;
    private int univinGyeonggi;
    private int univinGangwon;

    // 1.서울 2.경기도 3.강원도 4.충청남도 5.충청북도 6.전라남도 7.전라북도 8.제주도
    LatLng Seoul = new LatLng(37.41, 127);
    LatLng Gyeonggi = new LatLng(37.32, 127.4);
    LatLng Gangwon = new LatLng(37.81, 128.19);
    LatLng Chungcheongnamdo = new LatLng(36.54, 127.04);
    LatLng Chungcheongbukdo = new LatLng(36.89, 127.73);
    LatLng Jeollanamdo = new LatLng(35.12, 127.02);
    LatLng Jeollabukmdo = new LatLng(35.8, 127.11);
    LatLng Jeju = new LatLng(33.37, 126.53);
    LatLng Incheon = new LatLng(37.45,126.7);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        MainActivity.JsonParse jsonParse = new MainActivity.JsonParse();      // AsyncTask 생성
        jsonParse.execute("http://192.168.0.2/UnivList.php");
//        // 05.04 버튼 다시 추가
//        Button imageButton = (Button) findViewById(R.id.btn1);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    public class JsonParse extends AsyncTask<String, Void, String> {
        String TAG = "JsonParseTest";
        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            try {
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) serverURL.openConnection();

                httpURLConnection.setReadTimeout(1000);
                httpURLConnection.setConnectTimeout(1000);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                Log.d(TAG, sb.toString().trim());

                return sb.toString().trim();
            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                String errorString = e.toString();
                return null;
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onPostExecute(String fromdoInBackgroundString) {
            super.onPostExecute(fromdoInBackgroundString);
            if(fromdoInBackgroundString == null) {
                //u_incheon.setText("error");
                //u_seoul.setText("error");
            }
            else {
                jsonString = fromdoInBackgroundString;
                univArrayList = doParse();
                univinSeoul = 0;
                univinGyeonggi = 0;
                univinGangwon = 0;
                for(int i=0;i<univArrayList.size();i++) {
                    if(univArrayList.get(i).getAloc().equals("SEOUL")){//대학의 지역이 서울인 경우
                        univinSeoul +=1;
                    }else if(univArrayList.get(i).getAloc().equals("GYEONGGI")){//대학의 지역이 인천인 경우
                        univinGyeonggi +=1;
                    }else if(univArrayList.get(i).getAloc().equals("GANGWON")){//대학의 지역이 인천인 경우
                        univinGangwon +=1;
                    }
                }
            }
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        private ArrayList<Univlist> doParse() {
            ArrayList<Univlist> tmpUnivArray = new ArrayList<Univlist>();
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++) {
                    Univlist tmpUniv = new Univlist();
                    JSONObject item = jsonArray.getJSONObject(i);
                    tmpUniv.setAnum(item.getString("anum"));
                    tmpUniv.setAname(item.getString("aname"));
                    tmpUniv.setAloc(item.getString("aloc"));
                    tmpUnivArray.add(tmpUniv);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return tmpUnivArray;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // 서울의 위도 경도
        mMap.addMarker(new MarkerOptions()
                .position(Seoul)
                .title("서울"));
        // Map 초기 카메라 위치를 서울로
        LatLng startPoint = new LatLng(35.8,127.8);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint,7));

        mMap.addMarker(new MarkerOptions()
                .position(Gyeonggi)
                .title("경기도"));
        mMap.addMarker(new MarkerOptions()
                .position(Gangwon)
                .title("강원도"));
        mMap.addMarker(new MarkerOptions()
                .position(Chungcheongnamdo)
                .title("충청남도"));
        mMap.addMarker(new MarkerOptions()
                .position(Chungcheongbukdo)
                .title("충청북도"));
        mMap.addMarker(new MarkerOptions()
                .position(Jeollanamdo)
                .title("전라남도"));
        mMap.addMarker(new MarkerOptions()
                .position(Jeollabukmdo)
                .title("전라북도"));
        mMap.addMarker(new MarkerOptions()
                .position(Jeju)
                .title("제주도"));
        mMap.addMarker(new MarkerOptions()
                .position(Incheon)
                .title("인천"));


        // 리스너 지정
        mMap.setOnMarkerClickListener(this);

    }   //onMapReady

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {


        // 서울이라는 마커를 클릭했을 때
        if(marker.getTitle().equals("서울")){
            //Toast.makeText(this,"도서관 정보",Toast.LENGTH_LONG).show();
            Snackbar.make(findViewById(R.id.map),Integer.toString(univinSeoul)+"개의 대학이 있습니다",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), LabActivity.class);
                            startActivity(intent);
                        }
                    }).show();
        }

        else if(marker.getTitle().equals("강원도")){
            //Toast.makeText(this,"도서관 정보",Toast.LENGTH_LONG).show();
            Snackbar.make(findViewById(R.id.map),Integer.toString(univinGangwon)+"개의 대학이 있습니다",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }

        else if(marker.getTitle().equals("경기도")){
            //Toast.makeText(this,"도서관 정보",Toast.LENGTH_LONG).show();

            Snackbar.make(findViewById(R.id.map),Integer.toString(univinGyeonggi)+"개의 대학이 있습니다",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), Fake.class);
                            startActivity(intent);
                        }
                    }).show();
        }
        else if(marker.getTitle().equals("인천")){
            //Toast.makeText(this,"도서관 정보",Toast.LENGTH_LONG).show();

            Snackbar.make(findViewById(R.id.map),"인천 리스트",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), IncheonList.class);
                            startActivity(intent);
                        }
                    }).show();
        }


        return false;
    }


}
