package com.oht.UI.FirstStep;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oht.R;

public class FirstStepActivity extends AppCompatActivity {

    private ImageView test;

    @Override
    protected void onCreate(@NonNull Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_first_step);
        test = findViewById(R.id.test);
        test.bringToFront();
    }
}
