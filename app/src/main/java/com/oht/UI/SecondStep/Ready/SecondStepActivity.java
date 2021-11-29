package com.oht.UI.SecondStep.Ready;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;
import com.oht.UI.SecondStep.TrainBackground;

import java.util.Timer;
import java.util.TimerTask;

public class SecondStepActivity extends AppCompatActivity {

    private Button startBtn;
    private TrainBackground trainBackground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step);
        trainBackground = findViewById(R.id.second_step_canvas);
        startBtn = findViewById(R.id.start_btn);



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setVisibility(View.INVISIBLE);
                training();
            }
        });
    }

    private void training() {
        trainBackground.startCheck(false);
    }
}
