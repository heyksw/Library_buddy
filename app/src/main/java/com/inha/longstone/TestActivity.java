package com.inha.longstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;  //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag_chart frag_chart;
    private Frag_home frag_home;
    private Frag_mypage frag_mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        // 버튼 비활성화 코드
//        Button btn =(Button)findViewById(R.id.btn1);
//        btn.setEnabled(false);

        final CustomView indicator2 = (CustomView) findViewById(R.id.custom);

        bottomNavigationView = findViewById(R.id.navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.chart:
                        setFrag(0);
                        break;
                    case R.id.home:
                        setFrag(1);
                        break;
                    case R.id.mypage:
                        setFrag(2);
                        break;

                }
                return false;
            }
        });
        frag_chart = new Frag_chart();
        frag_home = new Frag_home();
        frag_mypage = new Frag_mypage();

        setFrag(1);  // 첫 화면은 home

    }

    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch(n){
            case 0:
                ft.replace(R.id.main_frame,frag_chart);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,frag_home);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,frag_mypage);
                ft.commit();
                break;

        }

    }
}