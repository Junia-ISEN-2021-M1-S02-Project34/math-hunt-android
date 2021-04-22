package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Login;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Button leaveMathHuntButton = (Button) findViewById(R.id.leaveMathHuntButton);

        leaveMathHuntButton.setOnClickListener(v -> {
            Intent intent = new Intent(SuccessActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


    }


}