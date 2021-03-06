package com.oht.UI.ThirdStep.Result;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oht.R;
import com.oht.UI.Main.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ThirdStepResultActivity extends AppCompatActivity {

    private SpannableString spannableString;
    private TextView result;
    private FloatingActionButton endBtn;
    private String content;
    private VideoView firstVideoView,secondVideoView;
    private List<String> files = new ArrayList<>();
    private int size;
    private File dir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_third_step_result);
        init();
    }
    private void init(){
        result = findViewById(R.id.third_step_result);
        endBtn = findViewById(R.id.thrid_step_result_end);
        firstVideoView = findViewById(R.id.third_video_view_1);
        secondVideoView = findViewById(R.id.third_video_view_2);

        getVideoSource();

        MediaController firstMediaController = new MediaController(this);
        MediaController secondMediaController = new MediaController(this);
        firstVideoView.setMediaController(firstMediaController);
        firstVideoView.setVideoPath(dir + "/"+ files.get(size-3));
        firstVideoView.requestFocus();
        firstVideoView.start();

        secondVideoView.setMediaController(secondMediaController);
        secondVideoView.setVideoPath(dir + "/"+ files.get(size-1));
        secondVideoView.requestFocus();
        secondVideoView.start();

        String newPoint = String.valueOf(randomPoint()) + "??? ";
        result.setText("????????? ???????????????" + newPoint +" ?????????.");
        content = result.getText().toString();

        spannableString = new SpannableString(content);
        //???????????? point ??? ?????? ??????????????? ??????
        int start = content.indexOf(newPoint);
        int end = start + newPoint.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        result.setText(spannableString);

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //boolean list ????????? ????????? ???????????? ??????
                MainActivity.check = new boolean[]{true, true, true};
                startActivity(intent);
                finish();
            }
        });
    }

    private int randomPoint(){
        int[] arr = {80,90,95};
        // 0 ~ 2
        int rand = (int) (Math.random() * 3);
        return arr[rand];
    }
    private void getVideoSource(){
        dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Optic_Habit_Training");
        File[] tempFile = dir.listFiles();

        size = tempFile.length;
        for(int i = 0; i<size; i++){
            files.add(tempFile[i].getName());
        }
        Collections.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
