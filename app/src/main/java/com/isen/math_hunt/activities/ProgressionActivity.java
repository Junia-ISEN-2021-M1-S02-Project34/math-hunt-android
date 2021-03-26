package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ListView;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.EnigmaAdapter;
import com.isen.math_hunt.adapters.GeoGroupAdapter;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;

import java.util.ArrayList;

public class ProgressionActivity extends AppCompatActivity {

    private ListView  geoGroupsListView;
    private ListView  enigmaListView;
    private GeoGroupAdapter geoGroupAdapter;
    private EnigmaAdapter enigmaAdapter;

    /**
    Create progressionActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);

        geoGroupsListView = (ListView) findViewById(R.id.geoGroupsListView);
        ArrayList<GeoGroup> geoGroupsList = new ArrayList<>();

        //TODO call dbb

        ArrayList<Enigma> enigmaArrayList = new ArrayList<>();
        enigmaArrayList.add(new Enigma("Enigme 1", 50));
        enigmaArrayList.add(new Enigma("Enigme 2", 50));
        enigmaArrayList.add(new Enigma("Enigme 3", 50));


        ArrayList<Enigma> enigmaArrayList2 = new ArrayList<>();
        enigmaArrayList2.add(new Enigma("Enigme 3", 50));
        enigmaArrayList2.add(new Enigma("Enigme 4", 50));
        enigmaArrayList2.add(new Enigma("Enigme 5", 50));

        geoGroupsList.add(new GeoGroup("GeoGroup 1",260, enigmaArrayList));
        geoGroupsList.add(new GeoGroup("GeoGroup 2",260,enigmaArrayList2));




        geoGroupAdapter = new GeoGroupAdapter(this,geoGroupsList);
        geoGroupsListView.setAdapter(geoGroupAdapter);


    }
}