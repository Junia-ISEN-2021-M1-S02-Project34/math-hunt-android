package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextInputLayout userTextField = findViewById(R.id.answerTextField);
        final TextInputLayout passwordTextField = findViewById(R.id.passewordTextField);
        final Button loginButton = findViewById(R.id.loginButton);




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(WaitingActivity.class);
            }
        });
    }

    public void switchActivity(Class activity){
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);
    }


}