package com.isen.math_hunt.fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgressionFragment extends Fragment {

    private ListView  geoGroupsListView;
    private ListView  enigmaListView;
    private ProgressionAdapter progressionAdapter;
    private EnigmaAdapter enigmaAdapter;
    private List<Progression> progressions;
    private String teamId;


    public ProgressionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_progression, null);

        teamId = getArguments().getString("TEAM_ID");
        geoGroupsListView = (ListView) mView.findViewById(R.id.geoGroupsListView);
        ArrayList<GeoGroup> geoGroupsList = new ArrayList<>();

        //TODO call dbb

        getTeamById(teamId);
        ArrayList<Enigma> enigmaArrayList = new ArrayList<>();


        progressionAdapter = new ProgressionAdapter(getActivity(),geoGroupsList);
        geoGroupsListView.setAdapter(progressionAdapter);

        return mView;
    }

    private void getTeamById(String id) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team team = response.body();
                    progressions = team.getProgression();


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
}
