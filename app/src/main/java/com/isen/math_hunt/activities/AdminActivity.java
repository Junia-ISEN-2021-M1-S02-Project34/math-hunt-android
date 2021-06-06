package com.isen.math_hunt.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.SpinnerAdapter;
import com.isen.math_hunt.entities.Game;
import com.isen.math_hunt.model.GetAllGames;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private GetAllGames games;
    private List<String> gameName = new ArrayList<>();

    private Spinner spin;
    private ProgressBar activityAdminProgressBar;
    private String token;

    private String currentChoice;
    private String currentId;

    private List<String> gamesId = new ArrayList<>();

    Button adminStartButton;
    Button adminStopButton;

    private List<Boolean> gameIsStarted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        SharedPreferences myPrefs = this.getSharedPreferences("USER_PREFERENCES",Context.MODE_PRIVATE);
        token = myPrefs.getString("ACCESS_TOKEN","");

        activityAdminProgressBar = (ProgressBar) findViewById(R.id.activityAdminProgressBar);
        activityAdminProgressBar.setVisibility(View.INVISIBLE);

        spin = (Spinner) findViewById(R.id.spinnerParty);
        spin.setOnItemSelectedListener(this);

        adminStartButton = (Button) findViewById(R.id.adminStartButton);
        adminStopButton = (Button) findViewById(R.id.adminStopButton);

        adminStopButton.setClickable(false);
        adminStopButton.setEnabled(false);

        adminStartButton.setClickable(false);
        adminStartButton.setEnabled(false);

        getAllGames();

        adminStartButton.setOnClickListener(v -> startGame(currentId, token));

        adminStopButton.setOnClickListener(v -> stopGame(currentId, token));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        currentChoice = gameName.get(position);
        currentId = gamesId.get(position);
        boolean currentGameIsStarted = gameIsStarted.get(position);

        Log.d("TAG", "currentChoice: " + currentChoice);
        Log.d("TAG", "currentId: " + currentId);

        if (currentGameIsStarted) {
            adminStartButton.setClickable(false);
            adminStartButton.setEnabled(false);

            adminStopButton.setClickable(true);
            adminStopButton.setEnabled(true);

        } else {
            adminStartButton.setClickable(true);
            adminStartButton.setEnabled(true);

            adminStopButton.setClickable(false);
            adminStopButton.setEnabled(false);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getAllGames() {
        Call<GetAllGames> call = RetrofitClient.getInstance().getMathHuntApiService().getAllGames(token);
        call.enqueue(new Callback<GetAllGames>() {
            @Override
            public void onResponse(Call<GetAllGames> call, Response<GetAllGames> response) {

                try {
                    activityAdminProgressBar.setVisibility(View.INVISIBLE);
                    games = response.body();


                    Log.d("getAllGames", "onResponse: good");
                    getGameInfo(games.getGames());

                    SpinnerAdapter customAdapter = new SpinnerAdapter(getApplicationContext(), gameName);
                    spin.setAdapter(customAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<GetAllGames> call, Throwable t) {
                Log.d("TAG", t.getMessage());

            }
        });
    }

    public void getGameInfo(final List<Game> gameList) {
        Log.d("getGameInfo", "onResponse: good");

        gameList.stream().forEach(
                game -> {
                    gameName.add(game.getName());
                    gamesId.add(game.get_id());
                    gameIsStarted.add(game.isStarted());

                });
    }

    private void startGame(String id, String token) {
        Call<ResponseBody> call = RetrofitClient.getInstance().getMathHuntApiService().startGame(id, token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(getApplicationContext(), "Partie commencée", Toast.LENGTH_SHORT).show();

                adminStartButton.setClickable(false);
                adminStartButton.setEnabled(false);

                adminStopButton.setClickable(true);
                adminStopButton.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void stopGame(String id, String token) {
        Call<ResponseBody> call = RetrofitClient.getInstance().getMathHuntApiService().stopGame(id, token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.d("startGame", "onResponse: " + id);
                Log.d("startGame", "onResponse: " + response);
                Toast.makeText(getApplicationContext(), "Partie arrêtée", Toast.LENGTH_SHORT).show();

                adminStartButton.setClickable(true);
                adminStartButton.setEnabled(true);

                adminStopButton.setClickable(false);
                adminStopButton.setEnabled(false);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}



