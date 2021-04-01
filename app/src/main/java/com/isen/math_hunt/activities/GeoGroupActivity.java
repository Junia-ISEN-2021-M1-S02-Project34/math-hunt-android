package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.isen.math_hunt.R;

public class GeoGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_groupe);
        Button geoGroupContinueButton = findViewById(R.id.geoGroupContinueButton);

        geoGroupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(EnigmaActivity.class);
            }
        });
    }

    public void switchActivity(Class activity){
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);

    }
}