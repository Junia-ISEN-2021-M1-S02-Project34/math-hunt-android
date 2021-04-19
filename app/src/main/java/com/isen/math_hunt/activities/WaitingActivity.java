package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.isen.math_hunt.api.MathHuntApiService;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Game;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.entities.Login;
import com.isen.math_hunt.entities.LoginResponse;
import com.isen.math_hunt.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitingActivity extends AppCompatActivity {

    private String teamId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        Bundle b = getIntent().getExtras();
        teamId = b.getString("TEAM_ID");
        token = b.getString("ACCESS_TOKEN");


        Button launchButton = findViewById(R.id.startButton);

        launchButton.setEnabled(false);
        launchButton.setClickable(false);

        private void getGameById(Game String id; id){
            Call<Game> call = RetrofitClient.getInstance().getMathHuntApiService().getGameById(id);
            call.enqueue(new Callback<Game>() {
                @Override
                public void onResponse(Call<Game> call, Response<Game> response) {

                    DialogInterface progressDialog = null;
                    boolean gameIsStarted;
                    try {
                        Game game = response.body();
                        Log.d("PROUT", "onResponse: " + response);
                        progressDialog.dismiss();
                        gameIsStarted = game.isStarted();

                        Intent intent = new Intent(WaitingActivity.this, GameActivity.class);
                        Bundle b = new Bundle();
                        b.putString("GAMESTART", String.valueOf(gameIsStarted));
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();


                    } catch (Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Probleme Technique", Toast.LENGTH_LONG).show();

                    }

                    if (gameIsStarted == true) {
                        launchButton.setEnabled(true);
                        launchButton.setClickable(true);
                    }
                }

                @Override
                public void onFailure(Call<Game> call, Throwable t) {

                }
            });
        }


        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitingActivity.this, GeoGroupActivity.class);
                Bundle b = new Bundle();
                b.putString("TEAM_ID", teamId);
                b.putString("ACCESS_TOKEN", token);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });
    }

}