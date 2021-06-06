package com.isen.math_hunt.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.GetGameById;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitingActivity extends AppCompatActivity {

    private String teamId;
    private String token;

    private Button launchButton;

    private GetGameById getGameById;
    private Timer t;

    private ProgressBar waitingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences myPrefs = this.getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
        teamId = myPrefs.getString("TEAM_ID","");
        token = myPrefs.getString("ACCESS_TOKEN","");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);



        waitingProgressBar = (ProgressBar) findViewById(R.id.waitingProgressBar);
        waitingProgressBar.setVisibility(View.VISIBLE);

        launchButton = findViewById(R.id.startButton);

        launchButton.setText("Patientez");

        launchButton.setEnabled(false);
        launchButton.setClickable(false);

        getTeamById(teamId);

        //Declare the timer
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() { getTeamById(teamId);
                                  }

                              }, 0, 5000);

        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitingActivity.this, GeoGroupActivity.class);
                Bundle b = new Bundle();
                intent.putExtras(b); //Put your id to your next Intent
                t.cancel();
                startActivity(intent);
                finish();
            }
        });
    }

    private void getGameById(String id) {
        Call<GetGameById> call = RetrofitClient.getInstance().getMathHuntApiService().getGameById(id, token);
        call.enqueue(new Callback<GetGameById>() {
            @Override
            public void onResponse(Call<GetGameById> call, Response<GetGameById> response) {

                boolean gameIsStarted;
                try {

                    waitingProgressBar.setVisibility(View.INVISIBLE);

                    getGameById = response.body();

                    if (getGameById.isStarted()) {
                        launchButton.setText("Commencer !");
                        launchButton.setEnabled(true);
                        launchButton.setClickable(true);
                    }else {
                        launchButton.setText("Patientez");
                        launchButton.setEnabled(false);
                        launchButton.setClickable(false);
                    }


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("PROUT", "onResponse: " + e.getMessage());

                }

            }

            @Override
            public void onFailure(Call<GetGameById> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getTeamById(String id) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id, token);
        call.enqueue(new Callback<Team>() {

            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team currentTeam = response.body();
                    boolean gameIsFinish = currentTeam.getGameFinished();

                    if (gameIsFinish){
                        Intent intent = new Intent(WaitingActivity.this, SuccessActivity.class);
                        t.cancel();
                        startActivity(intent);
                        finish();
                    }else{
                        getGameById(currentTeam.getGameId());

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.d("TAG", t.getMessage());

            }
        });
    }


}