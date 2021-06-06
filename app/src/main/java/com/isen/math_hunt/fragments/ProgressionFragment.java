package com.isen.math_hunt.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.EnigmaAdapter;
import com.isen.math_hunt.adapters.ProgressionAdapter;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgressionFragment extends Fragment {

    private ListView  geoGroupsListView;
    private ProgressionAdapter progressionAdapter;
    private List<Progression> progressions;
    private String teamId;

    private String token;

    private ProgressBar progressionProgressBar;



    public ProgressionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_progression, null);

        SharedPreferences myPrefs = this.getActivity().getSharedPreferences("USER_PREFERENCES", Context.MODE_PRIVATE);
        teamId = myPrefs.getString("TEAM_ID","");
        token = myPrefs.getString("ACCESS_TOKEN","");

        geoGroupsListView = (ListView) mView.findViewById(R.id.geoGroupsListView);



        progressionProgressBar = (ProgressBar) mView.findViewById(R.id.progressionProgressBar);
        progressionProgressBar.setVisibility(View.VISIBLE);

        getTeamById(teamId, token);


        return mView;
    }

    private void getTeamById(String id, String token) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id, token);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    progressionProgressBar.setVisibility(View.INVISIBLE);
                    Team team = response.body();
                    progressions = team.getProgression();


                    progressionAdapter = new ProgressionAdapter(getActivity(),progressions);
                    geoGroupsListView.setAdapter(progressionAdapter);


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
