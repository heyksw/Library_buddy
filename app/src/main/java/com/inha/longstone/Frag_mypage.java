package com.inha.longstone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Frag_mypage extends Fragment {
    private View view;
    int timer_sec = 0;
    int count = 0;
    private final Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage, container, false);
        TextView use_time = view.findViewById(R.id.use_time);

        ImageButton swap = view.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.13:8090"));
                startActivity(intent);

            }
        });


        //06.16 타이머 추가


        TimerTask second;
        second = new TimerTask() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {     // 60초만 보여줄 생각이라 이렇게 짬
                        if(timer_sec<10){
                            use_time.setText("02:34:0"+timer_sec);
                        }
                        else{
                            
                            use_time.setText("02:34:"+timer_sec);
                            if(timer_sec==59){
                                timer_sec=0;
                            }
                        }

                    }
                };

                handler.post(updater);

                timer_sec++;
            }
        };

        Timer timer = new Timer();
        timer.schedule(second,0,1000);


        return view;
    }





}
