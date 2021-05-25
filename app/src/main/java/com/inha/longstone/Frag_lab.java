
package com.inha.longstone;

import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.res.ColorStateList.valueOf;

public class Frag_lab extends Fragment {
    private View view;
    private String jsonString;
    private TextView tv, tv2, tv3;
    private Button st1 ,st2, st3, st4;
    private Button bar_button;
    ArrayList<Seat1> seatArrayList;
    ArrayList<Seat2> seatArrayList2;
    private String url;
    private int used = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_lab,container,false);
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
        Frag_lab.JsonParse jsonParse = new Frag_lab.JsonParse();      // AsyncTask 생성
        Frag_lab.JsonParse jsonParse2 = new Frag_lab.JsonParse();
        jsonParse.execute("http://165.246.43.224:8081/Seat.php");
        jsonParse2.execute("http://165.246.43.224:8081/Seat2.php");

            bar_button.setVisibility(View.INVISIBLE);

        Button secret = (Button) view.findViewById(R.id.secret);
        secret.setBackgroundDrawable(null);
        secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar_button.setVisibility(View.INVISIBLE);
                used = 0;
                Refresh();
            }
        });

        return view;
    }
    public class JsonParse extends AsyncTask<String, Void, String> {
        String TAG = "JsonParseTest";
        @Override
        protected String doInBackground(String... strings) {
            url = strings[0];
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
            if (fromdoInBackgroundString == null) {

                tv.setText("Server");

                tv2.setText("Connection");

                tv3.setText("Error");

            } else {
                jsonString = fromdoInBackgroundString;

                if(url == "http://165.246.43.224:8081/Seat.php") {
                    seatArrayList = doParse();

                    if(Integer.parseInt(seatArrayList.get(seatArrayList.size() - 1).getExist1()) == 0) {
                        st1.setBackgroundTintList(valueOf(0xFFD1D6F0));
                    }else if(Integer.parseInt(seatArrayList.get(seatArrayList.size() - 1).getExist1()) == 1) {
                        st1.setBackgroundTintList(valueOf(0xFF999393));
                        used += 1;
                    }else if(Integer.parseInt(seatArrayList.get(seatArrayList.size() - 1).getExist1()) == 2) {
                        st1.setBackgroundTintList(valueOf(0xFFE8ADB6));
                        used += 1;
                    }
                }else{
                    seatArrayList2 = doParse2();

                    if(Integer.parseInt(seatArrayList2.get(seatArrayList2.size() - 1).getExist2()) == 0) {
                        st2.setBackgroundTintList(valueOf(0xFFD1D6F0));
                    }else if(Integer.parseInt(seatArrayList2.get(seatArrayList2.size() - 1).getExist2()) == 1) {
                        st2.setBackgroundTintList(valueOf(0xFF999393));
                        used += 1;
                    }else if(Integer.parseInt(seatArrayList2.get(seatArrayList2.size() - 1).getExist2()) == 2) {
                        st2.setBackgroundTintList(valueOf(0xFFE8ADB6));
                        used += 1;
                    }
                }

                tv.setText(Integer.toString(used));
                int usable = 4 - used;//사용중 좌석 = total - used
                tv2.setText(Integer.toString(usable));
                int length = 164 ;
                ConstraintLayout.LayoutParams params= (ConstraintLayout.LayoutParams) bar_button.getLayoutParams();
                params.width = length * used;
                bar_button.setLayoutParams(params);
                bar_button.setVisibility(View.VISIBLE);
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

        private ArrayList<Seat1> doParse() {
            ArrayList<Seat1> tmpSeatArray = new ArrayList<Seat1>();
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Seat1 tmpSeat1 = new Seat1();
                    JSONObject item = jsonArray.getJSONObject(i);
                    tmpSeat1.setExist1(item.getString("exist1"));
                    tmpSeatArray.add(tmpSeat1);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return tmpSeatArray;
        }


        private ArrayList<Seat2> doParse2() {
            ArrayList<Seat2> tmpSeatArray2 = new ArrayList<Seat2>();
            try {
                JSONObject jsonObject2 = new JSONObject(jsonString);
                JSONArray jsonArray2 = jsonObject2.getJSONArray("results");
                for (int i = 0; i < jsonArray2.length(); i++) {
                    Seat2 tmpSeat2 = new Seat2();
                    JSONObject item = jsonArray2.getJSONObject(i);
                    tmpSeat2.setExist2(item.getString("exist2"));
                    tmpSeatArray2.add(tmpSeat2);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return tmpSeatArray2;
        }
    }
    private void Refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
