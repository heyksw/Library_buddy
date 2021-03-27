package com.inha.longstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 버튼 비활성화 코드
        Button btn =(Button)findViewById(R.id.btn1);
        btn.setEnabled(false);

        final CustomView indicator2 = (CustomView) findViewById(R.id.custom);
    }
}