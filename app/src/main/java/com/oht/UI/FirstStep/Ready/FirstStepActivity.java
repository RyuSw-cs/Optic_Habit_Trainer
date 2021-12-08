package com.oht.UI.FirstStep.Ready;

import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FirstStepActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private int time = 15;
    private int proceed = 7;
    private ProgressBar progressBar;
    private MediaRecorder mediaRecorder;
    private File fileName, fileDir;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera frontCamera;
    private Timer timer;
    private TimerTask timerTask;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step);
        surfaceView = findViewById(R.id.front_camera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                time--;
                proceed += 7;
                if (time == 0) {
                    stopRecording();
                    Intent intent = new Intent(getApplicationContext(), FirstStepEndActivity.class);
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
        //타이머 실행
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() throws IOException {
        progressBar = findViewById(R.id.first_step_progress_bar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        fileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Optic_Habit_Training");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        fileName = new File(fileDir.getPath() + "/" + simpleDateFormat.format(date) + ".mp4");

        recordAudio();
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
        try {
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