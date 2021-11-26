package com.oht.UI.SecondStep.Ready;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oht.R;

public class SecondStepReadyActivity extends AppCompatActivity {

    private FloatingActionButton nextStep;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_ready);

        nextStep = findViewById(R.id.second_step_next_step);
        imageView = findViewById(R.id.test);

        imageView.bringToFront();
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondStepLoadingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
