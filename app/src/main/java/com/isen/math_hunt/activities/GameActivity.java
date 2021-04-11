package com.isen.math_hunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.isen.math_hunt.R;
import com.isen.math_hunt.fragments.EnigmaFragment;
import com.isen.math_hunt.fragments.HintFragment;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.gameContainer, new EnigmaFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        BottomNavigationView bottomNavigationMenu = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.page_2) {
                    final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.gameContainer, new EnigmaFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                return false;
            }
        });

    }
}