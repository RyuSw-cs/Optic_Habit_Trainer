package com.oht.UI.Loading;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.oht.R;
import com.oht.UI.Main.MainActivity;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {
    
    private Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "권한 거부", Toast.LENGTH_SHORT).show();
            }
        };

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TedPermission.with(getApplicationContext())
                        .setPermissionListener(permissionListener)
                        .setRationaleMessage("카메라 사용을 위해 권한이 필요합니다")
                        .setDeniedMessage("앱 권한이 필요합니다.")
                        .setPermissions(Manifest.permission.CAMERA)
                        .check();
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
