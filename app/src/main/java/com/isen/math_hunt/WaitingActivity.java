package com.isen.math_hunt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.android.material.textfield.TextInputLayout;

public class WaitingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Button startgameButton = findViewById(R.id.startgameButton);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
    }
}