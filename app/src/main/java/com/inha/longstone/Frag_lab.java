package com.inha.longstone;

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

public class Frag_lab extends Fragment {
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
        view = inflater.inflate(R.layout.frag_lab,container,false);
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

        Frag_lab.JsonParse jsonParse = new Frag_lab.JsonParse();      // AsyncTask 생성
        jsonParse.execute("http://192.168.219.116/SelectAllPost.php");
        return view;
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
                tv.setText("error");
                tv2.setText("error");
                tv3.setText("error");
            }
            else {
                jsonString = fromdoInBackgroundString;
                seatArrayList = doParse();
                int total = 0;
                int used = 0;
                for(int i=0;i<seatArrayList.size();i++) {//테이블의 칼럼 갯수를 확인하여 좌석 전체 값을 불러옴
                    total = i;
                    if(Integer.parseInt(seatArrayList.get(i).getS_state()) == 1){//좌석 상태가 사용중이거나, 의심좌석일 경우 used 값이 증가
                        used +=1;
                    }else if(Integer.parseInt(seatArrayList.get(i).getS_state()) == 2){
                        used +=1;
                    }
                }
                int usable = Integer.parseInt(seatArrayList.get(total).getS_num()) - used;//사용중 좌석 = total - used
                tv.setText(Integer.toString(used));
                tv2.setText(Integer.toString(usable));
                tv3.setText(seatArrayList.get(total).getS_num());

                st1.setText("1");st2.setText("2");st3.setText("3");st4.setText("4");
                st5.setText("5");st6.setText("6");st7.setText("7");st8.setText("8");
                st9.setText("9");st10.setText("10");st11.setText("11");st12.setText("12");
                st13.setText("13");st14.setText("14");st15.setText("15");st16.setText("16");
                //좌석 번호 부여

                if(Integer.parseInt(seatArrayList.get(0).getS_state()) == 1) {
                    st1.setBackgroundTintList(ColorStateList.valueOf(0xFF999393) );
                }else if(Integer.parseInt(seatArrayList.get(0).getS_state()) == 2) {
                    st1.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(1).getS_state()) == 1){
                    st2.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(1).getS_state()) == 2) {
                    st2.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(2).getS_state()) == 1){
                    st3.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(2).getS_state()) == 2) {
                    st3.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(3).getS_state()) == 1){
                    st4.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(3).getS_state()) == 2) {
                    st4.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }

                if(Integer.parseInt(seatArrayList.get(4).getS_state()) == 1) {
                    st5.setBackgroundTintList(ColorStateList.valueOf(0xFF999393) );
                }else if(Integer.parseInt(seatArrayList.get(4).getS_state()) == 2) {
                    st5.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(5).getS_state()) == 1){
                    st6.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(5).getS_state()) == 2) {
                    st6.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(6).getS_state()) == 1){
                    st7.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(6).getS_state()) == 2) {
                    st7.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(7).getS_state()) == 1){
                    st8.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(7).getS_state()) == 2) {
                    st8.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }

                if(Integer.parseInt(seatArrayList.get(8).getS_state()) == 1) {
                    st9.setBackgroundTintList(ColorStateList.valueOf(0xFF999393) );
                }else if(Integer.parseInt(seatArrayList.get(8).getS_state()) == 2) {
                    st9.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(9).getS_state()) == 1){
                    st10.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(9).getS_state()) == 2) {
                    st10.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(10).getS_state()) == 1){
                    st11.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(10).getS_state()) == 2) {
                    st11.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(11).getS_state()) == 1){
                    st12.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(11).getS_state()) == 2) {
                    st12.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }

                if(Integer.parseInt(seatArrayList.get(12).getS_state()) == 1) {
                    st13.setBackgroundTintList(ColorStateList.valueOf(0xFF999393) );
                }else if(Integer.parseInt(seatArrayList.get(12).getS_state()) == 2) {
                    st13.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(13).getS_state()) == 1){
                    st14.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(13).getS_state()) == 2) {
                    st14.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(14).getS_state()) == 1){
                    st15.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(14).getS_state()) == 2) {
                    st15.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
                }
                if(Integer.parseInt(seatArrayList.get(15).getS_state()) == 1){
                    st16.setBackgroundTintList(ColorStateList.valueOf(0xFF999393));
                }else if(Integer.parseInt(seatArrayList.get(15).getS_state()) == 2) {
                    st16.setBackgroundTintList(ColorStateList.valueOf(0xFFE8ADB6) );
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

        private ArrayList<Seat> doParse() {
            ArrayList<Seat> tmpSeatArray = new ArrayList<Seat>();
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++) {
                    Seat tmpSeat = new Seat();
                    JSONObject item = jsonArray.getJSONObject(i);
                    tmpSeat.setS_num(item.getString("s_num"));
                    tmpSeat.setS_name(item.getString("s_name"));
                    tmpSeat.setS_state(item.getString("s_state"));
                    tmpSeat.setUser_num(item.getString("user_num"));
                    tmpSeatArray.add(tmpSeat);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return tmpSeatArray;
        }
    }
}

