package com.inha.longstone;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Frag_fake extends Fragment {
    private View view;
    private String jsonString;
    private TextView tv, tv2, tv3;
    private Button st1 ,st2 ,st3 ,st4;
    private Button st5 ,st6 ,st7 ,st8;
    private Button st9 ,st10 ,st11 ,st12;
    private Button st13 ,st14 ,st15 ,st16;
    ArrayList<Seat> seatArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_fake,container,false);
        st1 = (Button) view.findViewById(R.id.btn1);
        st2 = (Button) view.findViewById(R.id.btn2);
        st3 = (Button) view.findViewById(R.id.btn3);
        st4 = (Button) view.findViewById(R.id.btn4);


        tv = (TextView) view.findViewById(R.id.used);
        tv2 = (TextView) view.findViewById(R.id.usable);
        tv3 = (TextView) view.findViewById(R.id.total);


        Button secret = (Button) view.findViewById(R.id.secret);
        secret.setBackgroundDrawable(null);
        secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여기에 새로고침 구현

            }
        });



        Button secret2 = (Button) view.findViewById(R.id.secret2);

        //Activity labActivity = Frag_lab.this.getActivity();

        //secret2.setBackgroundDrawable(null);
        secret2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여기에 푸쉬알람 구현

                PendingIntent mPendingIntent = PendingIntent.getActivity(getActivity(),0,
                        new Intent(getActivity().getApplicationContext(),LabActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getActivity())
                                .setSmallIcon(R.drawable.icon)
                                .setContentTitle("알림 제목")
                                .setContentText("알림 내용")
                                .setDefaults(Notification.DEFAULT_VIBRATE)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true)
                                .setContentIntent(mPendingIntent);
                NotificationManager notificationManager =
                        (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

                notificationManager.notify(0,mBuilder.build());


            }




        });

        return view;
    }

}

