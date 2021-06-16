package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Test3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        View redBar = findViewById(R.id.redBar3);
        View blueBar = findViewById(R.id.blueBar3);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        redBar.startAnimation(animation);
        blueBar.startAnimation(animation);
    }
}
