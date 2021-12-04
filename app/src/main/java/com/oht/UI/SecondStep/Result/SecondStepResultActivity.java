package com.oht.UI.SecondStep.Result;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oht.R;
import com.oht.UI.Main.MainActivity;
import com.oht.UI.Train.TrainBackground;

public class SecondStepResultActivity extends AppCompatActivity {

    private SpannableString spannableString;
    private TextView result;
    private FloatingActionButton endBtn;
    private String content;
    private TrainBackground trainBackground;
    private boolean check = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_result);
        init();
    }
    private void init(){
        result = findViewById(R.id.third_step_result);
        endBtn = findViewById(R.id.thrid_step_result_end);
        trainBackground = findViewById(R.id.second_step_content);

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
}
