package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoGroupActivity extends AppCompatActivity {

    private String teamId;
    private String gameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_groupe);
        Button geoGroupContinueButton = findViewById(R.id.geoGroupContinueButton);

        Bundle b = getIntent().getExtras();
        teamId = b.getString("TEAM_ID");
        gameId = b.getString("GAME_ID");

        getTeamById(teamId);
        geoGroupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeoGroupActivity.this, GameActivity.class);
                Bundle b = new Bundle();
                b.putString("TEAM_ID", teamId);
                b.putString("GAME_ID", gameId);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });
    }

    private void getTeamById(String id) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team team = response.body();
                    Log.d("TAG", "onResponse: " + team.getUsername());

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