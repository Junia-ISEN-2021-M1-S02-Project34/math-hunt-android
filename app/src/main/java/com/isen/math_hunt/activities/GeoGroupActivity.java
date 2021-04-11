package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Enigma;

public class GeoGroupActivity extends AppCompatActivity {

    private String nextEnigmaId = "6063223667829e7540fbea1f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_groupe);
        Button geoGroupContinueButton = findViewById(R.id.geoGroupContinueButton);

        geoGroupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(GeoGroupActivity.this, EnigmaActivity.class);
              //  intent.putExtra("nextEnigmaId", nextEnigmaId);
              //  startActivity(intent);
              //  finish();
            }
        });
    }

    public void switchActivity(Class activity) {
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);

    }
}