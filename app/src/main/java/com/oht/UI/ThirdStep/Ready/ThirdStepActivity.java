package com.oht.UI.ThirdStep.Ready;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;
import com.oht.UI.FirstStep.Result.FirstStepEndActivity;
import com.oht.UI.ThirdStep.Result.ThirdStepEndActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ThirdStepActivity extends AppCompatActivity {

    private int time = 15;
    private int proceed = 7;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_third_step);

        init();

        final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                time--;
                proceed += 7;
                if(time == 0){
                    Intent intent = new Intent(getApplicationContext(), ThirdStepEndActivity.class);
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
        progressBar = findViewById(R.id.third_step_progress_bar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
    }
}
