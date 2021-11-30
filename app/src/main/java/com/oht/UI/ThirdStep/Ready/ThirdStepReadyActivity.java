package com.oht.UI.ThirdStep.Ready;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oht.R;

public class ThirdStepReadyActivity extends AppCompatActivity {

    private ImageView imageView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_third_step_ready);

        imageView = findViewById(R.id.test);
        floatingActionButton = findViewById(R.id.third_step_next_step);

        imageView.bringToFront();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThirdStepLoadingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
