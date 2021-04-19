package com.isen.math_hunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity implements CurrentEnigmaIdInterface {

    private String teamId;
    private String gameId;

    private int attemptsNumber;

    private String currentEnigmaId;
    private String currentGeoGroupId;

    private Team currentTeam;
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    private ArrayList<String> usedHintsIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle b = getIntent().getExtras();
        teamId = b.getString("TEAM_ID");
        gameId = b.getString("ACCESS_TOKEN");

        getTeamById(teamId);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.page_1:
                            Bundle enigmaBundle = new Bundle();
                            enigmaBundle.putString("TEAM_ID", teamId);
                            enigmaBundle.putString("CURRENT_ENIGMA_ID", currentEnigmaId);
                            enigmaBundle.putString("CURRENT_GEOGROUP_ID", currentGeoGroupId);
                            Log.d("attemptsNumber", "attemptsNumber: " + attemptsNumber);
                            enigmaBundle.putInt("ATTEMPTS_NUMBER", attemptsNumber);

                            Fragment enigmaFragment = new EnigmaFragment();
                            enigmaFragment.setArguments(enigmaBundle);
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.gameContainer, enigmaFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.page_2:
                            Bundle hintBundle = new Bundle();
                            hintBundle.putString("TEAM_ID", teamId);
                            hintBundle.putString("CURRENT_ENIGMA_ID", currentEnigmaId);

                            Fragment hintFragment = new HintFragment();
                            hintFragment.setArguments(hintBundle);
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.gameContainer, hintFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.page_3:
                            Bundle progressionBundle = new Bundle();
                            progressionBundle.putString("TEAM_ID", teamId);
                            Fragment progressionFragment = new ProgressionFragment();
                            progressionFragment.setArguments(progressionBundle);
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.gameContainer, progressionFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.page_4:
                            Bundle rankingBundle = new Bundle();
                            rankingBundle.putString("TEAM_ID", teamId);
                            Fragment rankingFragment = new RankingFragment();
                            rankingFragment.setArguments(rankingBundle);
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.gameContainer, rankingFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                    }


                    return true;
                }
            };

    private void getTeamById(String id) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id);
        call.enqueue(new Callback<Team>() {
            List<EnigmasProgression> enigmasProgression;

            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    currentTeam = response.body();
                    currentEnigmaId = currentTeam.getCurrentEnigmaId();
                    currentGeoGroupId = currentTeam.getCurrentGeoGroupId();


                    List<Progression> progressionList = currentTeam.getProgression();

                    getAttemptsNumber(progressionList,currentGeoGroupId,currentEnigmaId);


                    Bundle enigmaBundle = new Bundle();
                    enigmaBundle.putString("TEAM_ID", teamId);
                    enigmaBundle.putString("CURRENT_ENIGMA_ID", currentEnigmaId);
                    enigmaBundle.putString("CURRENT_GEOGROUP_ID", currentGeoGroupId);
                    enigmaBundle.putInt("ATTEMPTS_NUMBER", attemptsNumber);

                    Fragment enigmaFragment = new EnigmaFragment();
                    enigmaFragment.setArguments(enigmaBundle);
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