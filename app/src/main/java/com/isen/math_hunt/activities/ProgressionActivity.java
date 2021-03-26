package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.GeoGroupAdapter;
import com.isen.math_hunt.entities.GeoGroup;

import java.util.ArrayList;

public class ProgressionActivity extends AppCompatActivity {

    private ListView  geoGroupsListView;
    private GeoGroupAdapter geoGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);

        geoGroupsListView = (ListView) findViewById(R.id.geoGroupsListView);
        ArrayList<GeoGroup> geoGroupsList = new ArrayList<>();
        //TODO call dbb
        geoGroupsList.add(new GeoGroup("GeoGroup 1",260));
        geoGroupsList.add(new GeoGroup("GeoGroup 2",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 3",60));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));
        geoGroupsList.add(new GeoGroup("GeoGroup 4",150));


        geoGroupAdapter = new GeoGroupAdapter(this,geoGroupsList);
        geoGroupsListView.setAdapter(geoGroupAdapter);
    }
}