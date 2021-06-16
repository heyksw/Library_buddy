package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Test2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        View redBar = findViewById(R.id.redBar2);
        View blueBar = findViewById(R.id.blueBar2);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        redBar.startAnimation(animation);
        blueBar.startAnimation(animation);


    }
}
