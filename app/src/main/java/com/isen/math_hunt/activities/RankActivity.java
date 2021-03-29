package com.isen.math_hunt.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.EnigmaAdapter;
import com.isen.math_hunt.adapters.GeoGroupAdapter;
import com.isen.math_hunt.adapters.RankAdapter;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.entities.Team;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {


    private ListView rankListView;
     private RankAdapter rankAdapter;

    /**
     Create progressionActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rankListView = (ListView) findViewById(R.id.rankList);
        ArrayList<Team> teamList = new ArrayList<>();

        teamList.add(new Team("Team 1", 150));
        teamList.add(new Team("Team 2", 150));

        rankAdapter = new RankAdapter(this,teamList);
        rankListView.setAdapter(rankAdapter);


    }
}
