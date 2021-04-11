package com.isen.math_hunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.isen.math_hunt.R;
import com.isen.math_hunt.fragments.EnigmaFragment;
import com.isen.math_hunt.fragments.HintFragment;
import com.isen.math_hunt.fragments.ProgressionFragment;
import com.isen.math_hunt.fragments.RankingFragment;

public class GameActivity extends AppCompatActivity {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        transaction.replace(R.id.gameContainer, new EnigmaFragment());
        transaction.addToBackStack(null);
        transaction.commit();
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
                            selectedFragment = new EnigmaFragment();
                            break;
                        case R.id.page_2:
                            selectedFragment = new HintFragment();
                            break;
                        case R.id.page_3:
                            selectedFragment = new ProgressionFragment();
                            break;
                        case R.id.page_4:
                            selectedFragment = new RankingFragment();
                            break;
                    }
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.gameContainer, selectedFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;
                }
            };
}