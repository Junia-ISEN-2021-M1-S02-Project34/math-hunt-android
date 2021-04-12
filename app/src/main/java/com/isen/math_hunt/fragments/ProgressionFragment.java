package com.isen.math_hunt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.EnigmaAdapter;
import com.isen.math_hunt.adapters.ProgressionAdapter;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;

import java.util.ArrayList;

public class ProgressionFragment extends Fragment {

    private ListView  geoGroupsListView;
    private ListView  enigmaListView;
    private ProgressionAdapter progressionAdapter;
    private EnigmaAdapter enigmaAdapter;

    public ProgressionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_progression, null);

        geoGroupsListView = (ListView) mView.findViewById(R.id.geoGroupsListView);
        ArrayList<GeoGroup> geoGroupsList = new ArrayList<>();

        //TODO call dbb

        ArrayList<Enigma> enigmaArrayList = new ArrayList<>();


        progressionAdapter = new ProgressionAdapter(getActivity(),geoGroupsList);
        geoGroupsListView.setAdapter(progressionAdapter);

        return mView;
    }
}
