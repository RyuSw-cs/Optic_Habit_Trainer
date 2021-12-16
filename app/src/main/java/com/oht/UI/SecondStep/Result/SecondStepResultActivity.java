package com.oht.UI.SecondStep.Result;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import com.oht.UI.Train.TrainBackground;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SecondStepResultActivity extends AppCompatActivity {

    private SpannableString spannableString;
    private TextView result;
    private FloatingActionButton endBtn;
    private String content;
    private boolean check = false;
    private VideoView videoView;
    private List<String> files = new ArrayList<>();
    private int size;
    private File dir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_result);
        init();
    }
    private void init(){
        result = findViewById(R.id.third_step_result);
        endBtn = findViewById(R.id.thrid_step_result_end);
        videoView = findViewById(R.id.second_video_view);

        getVideoSource();

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(dir + "/"+ files.get(size-1));
        videoView.requestFocus();
        videoView.start();

        String newPoint = String.valueOf(randomPoint()) + "점 ";
        result.setText("당신의 시력습관은\n" + newPoint +"입니다.");
        content = result.getText().toString();

        spannableString = new SpannableString(content);
        //문자열중 point 의 처음 시작지점을 찾음
        int start = content.indexOf(newPoint);
        int end = start + newPoint.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        result.setText(spannableString);

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //boolean list 형태로 받아서 체크버튼 확인
                MainActivity.check = new boolean[]{true, true, false};
                startActivity(intent);
                finish();
            }
        });
    }

    private int randomPoint(){
        int[] arr = {60,70,75};
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
