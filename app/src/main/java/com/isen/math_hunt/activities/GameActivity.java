package com.isen.math_hunt.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.fragments.EnigmaFragment;
import com.isen.math_hunt.fragments.HintFragment;
import com.isen.math_hunt.fragments.ProgressionFragment;
import com.isen.math_hunt.fragments.RankingFragment;
import com.isen.math_hunt.interfaces.CurrentEnigmaIdInterface;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity implements CurrentEnigmaIdInterface {

    private String teamId;
    private String token;

    private int attemptsNumber;

    private String currentEnigmaId;
    private String currentGeoGroupId;

    private Team currentTeam;
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    private int score;

    private String gameId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences myPrefs = this.getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
        teamId = myPrefs.getString("TEAM_ID","");
        token = myPrefs.getString("ACCESS_TOKEN","");
        currentGeoGroupId = myPrefs.getString("CURRENT_GEOGROUP_ID","");

        getTeamById(teamId, token);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {

                switch (item.getItemId()) {
                    case R.id.page_1:
                        Fragment enigmaFragment = new EnigmaFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.gameContainer, enigmaFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.page_2:

                        Fragment hintFragment = new HintFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.gameContainer, hintFragment);
                        transaction.commit();
                        break;
                    case R.id.page_3:

                        Fragment progressionFragment = new ProgressionFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.gameContainer, progressionFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.page_4:

                        Fragment rankingFragment = new RankingFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.gameContainer, rankingFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }


                return true;
            };

    private void getTeamById(String id,String token) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id, token);
        call.enqueue(new Callback<Team>() {

            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    currentTeam = response.body();
                    currentEnigmaId = currentTeam.getCurrentEnigmaId();
                    currentGeoGroupId = currentTeam.getCurrentGeoGroupId();
                    gameId = currentTeam.getGameId();
                    score = currentTeam.getScore();

                    List<Progression> progressionList = currentTeam.getProgression();

                    getAttemptsNumber(progressionList,currentGeoGroupId,currentEnigmaId);

                    SharedPreferences myPrefs = GameActivity.this.getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.putString("TEAM_ID", teamId);
                    editor.putString("CURRENT_ENIGMA_ID", currentEnigmaId);
                    editor.putString("CURRENT_GEOGROUP_ID", currentGeoGroupId);
                    editor.putInt("ATTEMPTS_NUMBER", score);
                    editor.putInt("SCORE", attemptsNumber);
                    editor.putString("GAME_ID", gameId);
                    editor.apply();

                    Fragment enigmaFragment = new EnigmaFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.gameContainer, enigmaFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

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

    @Override
    public void updateCurrentEnigmaId(String newEnigmaId) {

        this.currentEnigmaId = newEnigmaId;


    }



    public void getAttemptsNumber(final List<Progression> progressionList, final String geoGroupId, final String enigmaId) {
        progressionList.stream().filter(progression -> progression.getGeoGroupId().equals(geoGroupId)).forEach(
                progression -> {
                    List<EnigmasProgression> enigmasProgressionList = progression.getEnigmasProgression();

                    enigmasProgressionList.stream().filter(enigmasProgression -> enigmasProgression.getEnigmaId().equals(enigmaId)).forEach(
                            enigmasProgression -> {
                                attemptsNumber = enigmasProgression.getAttemptsNumber();
                            }
                    );
                }
        );
    }

    public void updateAttemptsNumber(int attemptsNumber){
        this.attemptsNumber = attemptsNumber;
    }




}