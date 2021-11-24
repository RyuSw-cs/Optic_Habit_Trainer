package com.oht.UI.FirstStep.Ready;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oht.R;

public class FirstStepReadyActivity extends AppCompatActivity {

    private FloatingActionButton nextStep;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step_ready);
        init();
    }
    private void init(){
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
}