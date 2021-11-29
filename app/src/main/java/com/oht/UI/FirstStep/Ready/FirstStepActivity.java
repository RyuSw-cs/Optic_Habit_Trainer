package com.oht.UI.FirstStep.Ready;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;

import java.util.Timer;
import java.util.TimerTask;

public class FirstStepActivity extends AppCompatActivity {

    private int time = 15;
    private int proceed = 7;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step);
        init();

        final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                time--;
                proceed += 7;
                if(time == 0){
                    Intent intent = new Intent(getApplicationContext(), FirstStepEndActivity.class);
                    startActivity(intent);
                    finish();
                }
                progressBar.setProgress(proceed);
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

    private void init(){
        progressBar = findViewById(R.id.first_step_progress_bar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
    }
}
