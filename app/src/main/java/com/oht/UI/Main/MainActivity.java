package com.oht.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.oht.Data.Step;
import com.oht.R;
import com.oht.UI.Main.Adapter.MainRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private List<Step>list = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    public static boolean[] check = {false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListData();
        init();
    }

    private void init(){
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = new boolean[]{false,false,false};
                mainRecyclerAdapter = new MainRecyclerAdapter(list, check,getApplicationContext());
                recyclerView.setAdapter(mainRecyclerAdapter);
            }
        });

        recyclerView = findViewById(R.id.main_recycler_view);
        mainRecyclerAdapter = new MainRecyclerAdapter(list, check,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainRecyclerAdapter);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        };

        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("애플리케이션 사용을 위해 권한이 필요합니다")
                .setDeniedMessage("앱 권한이 필요합니다.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }
    private void addListData(){
        list.clear();
        list.add(new Step("STEP 1 - Reading Test 1","글씨를 읽을 때 시습관을 관찰합니다."));
        list.add(new Step("STEP 2 - Training","공의 움직임에 따라 눈동자의 움직임을 관찰하고 학습합니다."));
        list.add(new Step("STEP 3 - Reading Test 2","시력 습관을 개선하기 위한 테스트를 진행합니다."));
    }
}