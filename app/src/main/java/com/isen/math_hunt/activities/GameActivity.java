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
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.fragments.EnigmaFragment;
import com.isen.math_hunt.fragments.HintFragment;
import com.isen.math_hunt.fragments.ProgressionFragment;
import com.isen.math_hunt.fragments.RankingFragment;
import com.isen.math_hunt.interfaces.CurrentEnigmaIdInterface;
import com.isen.math_hunt.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity implements CurrentEnigmaIdInterface {

    private String teamId;
    private String gameId;

    private String currentEnigmaId;
    private String currentGeoGroupId;

    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle b = getIntent().getExtras();
        teamId = b.getString("TEAM_ID");
        gameId = b.getString("GAME_ID");

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
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team team = response.body();
                    Log.d("TAG", "onResponse: " + response);
                    currentEnigmaId = team.getCurrentEnigmaId();
                    currentGeoGroupId = team.getCurrentGeoGroupId();


                    Bundle enigmaBundle = new Bundle();
                    enigmaBundle.putString("TEAM_ID", teamId);
                    enigmaBundle.putString("CURRENT_ENIGMA_ID", currentEnigmaId);
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
}