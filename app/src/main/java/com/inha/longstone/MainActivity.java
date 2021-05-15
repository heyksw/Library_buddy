package com.inha.longstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    // 1.서울 2.경기도 3.강원도 4.충청남도 5.충청북도 6.전라남도 7.전라북도 8.제주도
    LatLng Seoul = new LatLng(37.41, 127);
    LatLng Gyeonggi = new LatLng(37.32, 127.4);
    LatLng Gangwon = new LatLng(37.81, 128.19);
    LatLng Chungcheongnamdo = new LatLng(36.54, 127.04);
    LatLng Chungcheongbukdo = new LatLng(36.89, 127.73);
    LatLng Jeollanamdo = new LatLng(35.12, 127.02);
    LatLng Jeollabukmdo = new LatLng(35.8, 127.11);
    LatLng Jeju = new LatLng(33.37, 126.53);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     *
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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

        // 리스너 지정
        mMap.setOnMarkerClickListener(this);


        // 아 이거 다 개 뻘짓이었음. 아 ㅋㅋ

//        MarkerOptions markerOpt = new MarkerOptions();
//        markerOpt.position(Seoul)
//                .title("서울")
//                .snippet("하이");
//        markerOpt.position(Gangwon)
//                .title("강원도")
//                .snippet("하이");
//
//        //Set Custom InfoWindow Adapter
//        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MainActivity.this);
//        mMap.setInfoWindowAdapter(adapter);
//
//        mMap.addMarker(markerOpt).showInfoWindow();



    }   //onMapReady


    // 나중에 커스텀 스낵바로 디자인은 바꿀 것임.
    // 일단 서울하고 강원도만 테스트 용으로 만들었음.
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {


        // 서울이라는 마커를 클릭했을 때
        if(marker.getTitle().equals("서울")){
            //Toast.makeText(this,"도서관 정보",Toast.LENGTH_LONG).show();
            Snackbar.make(findViewById(R.id.map),"테스트 페이지",Snackbar.LENGTH_INDEFINITE)
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
            Snackbar.make(findViewById(R.id.map),"강원도 도서관 정보",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }

        else if(marker.getTitle().equals("경기도")){
            //Toast.makeText(this,"도서관 정보",Toast.LENGTH_LONG).show();

            Snackbar.make(findViewById(R.id.map),"인하대학교",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                            startActivity(intent);
                        }
                    }).show();
        }


        return false;
    }


}