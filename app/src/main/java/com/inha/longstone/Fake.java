package com.inha.longstone;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fake extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;  //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag_chart frag_chart;
    private Frag_mypage frag_mypage;
    private Frag_fake frag_fake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);


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
        frag_mypage = new Frag_mypage();
        frag_fake = new Frag_fake();

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
                ft.replace(R.id.main_frame,frag_fake);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,frag_mypage);
                ft.commit();
                break;

        }

    }
}
