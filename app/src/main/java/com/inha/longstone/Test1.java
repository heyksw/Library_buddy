package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Test1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        View redBar = findViewById(R.id.redBar);
        View blueBar = findViewById(R.id.blueBar);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        redBar.startAnimation(animation);
        blueBar.startAnimation(animation);



    }
}
