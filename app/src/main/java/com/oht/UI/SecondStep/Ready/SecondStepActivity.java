package com.oht.UI.SecondStep.Ready;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;
import com.oht.UI.Train.TrainBackground;
import com.oht.UI.SecondStep.Result.SecondStepEndActivity;

import java.util.concurrent.Semaphore;

public class SecondStepActivity extends AppCompatActivity {

    private Button startBtn;
    public TrainBackground trainBackground;
    private boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step);
        trainBackground = (TrainBackground) findViewById(R.id.second_step_canvas);
        startBtn = findViewById(R.id.start_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainBackground.check = false;
                startBtn.setVisibility(View.INVISIBLE);
                training();
            }
        });
    }

    public void training() {
        //여기서 UI를 관리해야함
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!check) {
                    if (trainBackground.size < trainBackground.count) {
                        check = true;
                        Thread.currentThread().interrupt();
                        Intent intent = new Intent(getApplicationContext(), SecondStepEndActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    try {
                        Thread.sleep(153);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            trainBackground.invalidate();
                        }
                    });
                }
            }
        }).start();
    }
}

