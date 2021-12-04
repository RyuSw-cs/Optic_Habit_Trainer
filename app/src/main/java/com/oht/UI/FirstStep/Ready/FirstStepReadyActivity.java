package com.oht.UI.FirstStep.Ready;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Preview;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oht.R;

public class FirstStepReadyActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private FloatingActionButton nextStep;
    private ImageView imageView;
    private Button button;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    boolean previewing = false;

    private String stringPath = "/sdcard/samplevideo.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step_ready);
        init();
    }

    private void init() {
        cameraOpen();
        nextStep = findViewById(R.id.first_step_next_step);
        imageView = findViewById(R.id.test);
        imageView.bringToFront();
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FirstStepLoadingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    private void cameraOpen() {
        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = findViewById(R.id.cameraPreview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        camera = Camera.open();
        if (camera != null) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                previewing = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}