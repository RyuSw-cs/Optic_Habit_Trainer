package com.oht.UI.Loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;
import com.oht.UI.main.MainActivity;

public class LoadingActivity extends AppCompatActivity {
    
    private Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        },2000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
