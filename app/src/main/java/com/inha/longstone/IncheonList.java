package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IncheonList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incheon_list);

        Button Inha = findViewById(R.id.Inha);
        Button univ1 = findViewById(R.id.univ1);
        Button univ2 = findViewById(R.id.univ2);
        Button univ3 = findViewById(R.id.univ3);
        Button univ4 = findViewById(R.id.univ4);
        Button univ5 = findViewById(R.id.univ5);
        Button univ6 = findViewById(R.id.univ6);
        Button univ7 = findViewById(R.id.univ7);

        univ1.setBackgroundDrawable(null);
        univ2.setBackgroundDrawable(null);
        univ3.setBackgroundDrawable(null);
        univ4.setBackgroundDrawable(null);
        univ5.setBackgroundDrawable(null);
        univ6.setBackgroundDrawable(null);
        univ7.setBackgroundDrawable(null);
        Inha.setBackgroundDrawable(null);


        Inha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), InhaList.class);
                startActivity(intent);
            }
        });
    }
}
