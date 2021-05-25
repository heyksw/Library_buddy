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
import androidx.constraintlayout.widget.ConstraintLayout;
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

import static android.content.res.ColorStateList.valueOf;

public class Frag_fake extends Fragment {
    private View view;
    private String jsonString;
    private TextView tv, tv2, tv3;
    private Button st1 ,st2 ,st3 ,st4;
    ArrayList<Seat> seatArrayList;
    private int count = 0;
    private Button bar_button;
    private int used = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_fake,container,false);
        st1 = (Button) view.findViewById(R.id.btn1);
        st2 = (Button) view.findViewById(R.id.btn2);
        st3 = (Button) view.findViewById(R.id.btn3);
        st4 = (Button) view.findViewById(R.id.btn4);
        bar_button = (Button) view.findViewById(R.id.bar_button);
        st1.setText("1");st2.setText("2");st3.setText("3");st4.setText("4");


        tv = (TextView) view.findViewById(R.id.used);
        tv2 = (TextView) view.findViewById(R.id.usable);
        tv3 = (TextView) view.findViewById(R.id.total);

        tv.setText("0");
        tv2.setText("0");
        tv3.setText("4");

        bar_button.setVisibility(View.INVISIBLE);

        Button secret = (Button) view.findViewById(R.id.secret);
        secret.setBackgroundDrawable(null);
        secret.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) bar_button.getLayoutParams();

                if (count == 0) {
                    st1.setBackgroundTintList(valueOf(0xFF999393));
                    params.width = 164;
                    bar_button.setLayoutParams(params);
                    bar_button.setVisibility(View.VISIBLE);
                    tv.setText("1");
                    tv2.setText("3");
                    count += 1;
                } else if (count == 1) {
                    st2.setBackgroundTintList(valueOf(0xFF999393));
                    params.width = 328;
                    bar_button.setLayoutParams(params);
                    tv.setText("2");
                    tv2.setText("2");
                    count += 1;
                } else if (count == 2) {
                    st2.setBackgroundTintList(valueOf(0xFFE8ADB6));
                    count += 1;
                } else if (count == 3) {
                    st1.setBackgroundTintList(valueOf(0xFFD1D6F0));
                    st2.setBackgroundTintList(valueOf(0xFFD1D6F0));
                    tv.setText("0");
                    tv2.setText("4");
                    params.width = 0;
                    bar_button.setLayoutParams(params);
                    bar_button.setVisibility(View.INVISIBLE);
                    count = 0;

                }
            }
        });

        Button secret2 = (Button) view.findViewById(R.id.secret2);

        //Activity labActivity = Frag_lab.this.getActivity();

        secret2.setBackgroundDrawable(null);
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
