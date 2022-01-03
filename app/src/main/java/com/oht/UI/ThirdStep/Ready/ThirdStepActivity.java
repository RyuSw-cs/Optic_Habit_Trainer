package com.oht.UI.ThirdStep.Ready;

import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;
import com.oht.UI.FirstStep.Result.FirstStepEndActivity;
import com.oht.UI.ThirdStep.Result.ThirdStepEndActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ThirdStepActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private int time = 16;
    private int proceed = 0;
    private ProgressBar progressBar;
    private MediaRecorder mediaRecorder;
    private File fileName, fileDir;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera frontCamera;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_third_step);
        surfaceView = findViewById(R.id.third_front_camera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        init();

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                time--;
                proceed += 7;
                if (time == 0) {
                    stopRecording();
                    Intent intent = new Intent(getApplicationContext(), ThirdStepEndActivity.class);
                    startActivity(intent);
                    finish();
                }
                progressBar.setProgress(proceed);
            }
        };

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
    }

    private void init() {
        progressBar = findViewById(R.id.third_step_progress_bar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        fileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Optic_Habit_Training");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        fileName = new File(fileDir.getPath() + "/" + simpleDateFormat.format(date) + "_3.mp4");

        recordAudio();
    }

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

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setCamera(frontCamera);
            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setVideoEncodingBitRate(1920*1080);
            mediaRecorder.setVideoSize(1280,720);
            mediaRecorder.setVideoFrameRate(30);
            switch (getWindowManager().getDefaultDisplay().getRotation()){
                case Surface.ROTATION_0:
                    mediaRecorder.setOrientationHint(270);
                    break;
                case Surface.ROTATION_90:
                    mediaRecorder.setOrientationHint(0);
                    break;
                case Surface.ROTATION_180:
                    mediaRecorder.setOrientationHint(90);
                    break;
                case Surface.ROTATION_270:
                    mediaRecorder.setOrientationHint(180);
                    break;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mediaRecorder.setOutputFile(fileName);
            }
            else{
                String temp = fileName.getAbsoluteFile().toString();
                mediaRecorder.setOutputFile(temp);
            }
            mediaRecorder.prepare();
            mediaRecorder.start();
            timer.schedule(timerTask, 0, 1000);
        } catch (Exception e) {
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