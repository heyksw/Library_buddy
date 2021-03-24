package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imageButton = (Button) findViewById(R.id.btn1);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);

                URLConnector url = new URLConnector();
                url.start();
                try {
                    url.join();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                String result = url.getTemp();

                System.out.println(ParseJSON(result));
                ParseJSON(result);
            }
        });
    }
    public String ParseJSON(String target){

        try {
            JSONObject json = new JSONObject(target);

            JSONArray arr = json.getJSONArray("seat");

            for(int i = 0; i < arr.length(); i++){
                JSONObject json2 = arr.getJSONObject(i);

                System.out.println(json2.getString("s_num"));
                System.out.println(json2.getString("s_state"));
                System.out.println(json2.getString("s_name"));
                System.out.println(json2.getString("user_num"));
            }

            return "";
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
