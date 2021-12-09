package com.oht.UI.SecondStep.Ready;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;
import com.oht.UI.Train.TrainBackground;
import com.oht.UI.SecondStep.Result.SecondStepEndActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SecondStepActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private Button startBtn;
    public TrainBackground trainBackground;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean check = false;
    private Camera frontCamera;
    private MediaRecorder mediaRecorder;
    private File fileName, fileDir;
    private boolean check_camera = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step);
        trainBackground = (TrainBackground) findViewById(R.id.second_step_canvas);
        startBtn = findViewById(R.id.start_btn);

        surfaceView = findViewById(R.id.second_front_camera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        init();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                while(mediaRecorder == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mediaRecorder.start();
                trainBackground.check = false;
                startBtn.setVisibility(View.INVISIBLE);
                training();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        fileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Optic_Habit_Training");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        fileName = new File(fileDir.getPath() + "/" + simpleDateFormat.format(date) + "_2.mp4");

        recordAudio();
    }

    public void training() {
        //여기서 UI를 관리해야함
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                while (!check) {
                    if (trainBackground.size < trainBackground.count) {
                        check = true;
                        stopRecording();
                        Thread.currentThread().interrupt();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void recordAudio() {
        try {
            int i;
            for (i = 0; i < Camera.getNumberOfCameras(); i++) {
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(i, info);
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    break;
                }
            }
            frontCamera = getCamera(i);
            frontCamera.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    public void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            frontCamera.release();
            frontCamera = null;
        }
        Intent intent = new Intent(getApplicationContext(), SecondStepEndActivity.class);
        startActivity(intent);
        finish();
    }

    private Camera getCamera(int i) {
        Camera c = null;
        try {
            c = Camera.open(i);
        } catch (Exception e) {

        }
        return c;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setCamera(frontCamera);
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setVideoEncodingBitRate(1024 * 1024);
        mediaRecorder.setVideoFrameRate(15);
        mediaRecorder.setOrientationHint(270);
        mediaRecorder.setOutputFile(fileName);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}

