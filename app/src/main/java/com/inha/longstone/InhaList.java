package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InhaList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inha_list);

        // 정석
        Button button1 = findViewById(R.id.button1);
        button1.setBackgroundDrawable(null);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Test1.class);
                startActivity(intent);
            }
        });

        // 60주년 월천
        Button button2 = findViewById(R.id.button2);
        button2.setBackgroundDrawable(null);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Test2.class);
                startActivity(intent);
            }
        });

        // 하이테크 해동
        Button button3 = findViewById(R.id.button3);
        button3.setBackgroundDrawable(null);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Test3.class);
                startActivity(intent);
            }
        });


        Button button4 = findViewById(R.id.button4);
        button4.setBackgroundDrawable(null);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
            }
        });

    }
}
