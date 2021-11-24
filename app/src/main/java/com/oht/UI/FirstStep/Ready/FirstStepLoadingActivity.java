package com.oht.UI.FirstStep.Ready;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;

import java.util.Timer;
import java.util.TimerTask;

public class FirstStepLoadingActivity extends AppCompatActivity {

    private int time = 4;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step_loading);
        textView = findViewById(R.id.loading_count);

        final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                time--;
                if(time == 0){
                    Intent intent = new Intent(getApplicationContext(), FirstStepActivity.class);
                    startActivity(intent);
                    finish();
                }
                textView.setText(String.valueOf(time));
            }
        };

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        //타이머 실행
        timer.schedule(timerTask, 0, 1000);
    }
}

