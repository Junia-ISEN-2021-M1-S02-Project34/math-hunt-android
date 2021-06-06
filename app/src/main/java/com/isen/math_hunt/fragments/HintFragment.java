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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.HintAdapter;
import com.isen.math_hunt.adapters.ProgressionAdapter;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.HintList;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HintFragment extends Fragment {

    private ListView hintListView;
    private HintAdapter hintAdapter;
    private String teamId;
    private String currentEnigmaId;
    private ProgressDialog progressDialog;
    private List<Hint> hintList = new ArrayList<>();
    private List<Progression> progressions;
    private List<EnigmasProgression> enigmasProgression;
    private String token;

    private List<String> usedHintsIds;

    private ProgressBar hintProgressBar;

    public HintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_hint, null);
        SharedPreferences myPrefs = this.getActivity().getSharedPreferences("USER_PREFERENCES", Context.MODE_PRIVATE);
        teamId = myPrefs.getString("TEAM_ID","");
        token = myPrefs.getString("ACCESS_TOKEN","");
        currentEnigmaId = myPrefs.getString("CURRENT_ENIGMA_ID","");

        Log.d("HINTS", "onCreateView: " + teamId + token + currentEnigmaId);


        hintProgressBar = (ProgressBar) mView.findViewById(R.id.hintProgressBar);
        hintProgressBar.setVisibility(View.VISIBLE);

        getTeamById(teamId, token);

        hintListView = (ListView) mView.findViewById(R.id.hintListView);

        return mView;
    }

    private void getHintsByEnigmaId(String id, String token) {
        Call<HintList> call = RetrofitClient.getInstance().getMathHuntApiService().getHintsByEnigmaId(id, token);
        call.enqueue(new Callback<HintList>() {
            @Override
            public void onResponse(Call<HintList> call, Response<HintList> response) {

                try {
                    hintProgressBar.setVisibility(View.INVISIBLE);

                    HintList hints = response.body();

                    hintList = hints.getHints();

                    hintAdapter = new HintAdapter(getActivity(), hintList, usedHintsIds, teamId,token);
                    hintListView.setAdapter(hintAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<HintList> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }

    private void getTeamById(String id, String token) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id, token);
        call.enqueue(new Callback<Team>() {
            List<EnigmasProgression> enigmasProgression;

            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team currentTeam = response.body();
                    List<Progression> progressionList = currentTeam.getProgression();

                    teamId = currentTeam.get_id();

                    int indexOfGeoGroupId = IntStream.range(0, progressionList.size())
                            .filter(i -> progressionList.get(i).getGeoGroupId().equals(currentTeam.getCurrentGeoGroupId()))
                            .findAny()
                            .orElse(-1);

                    List<EnigmasProgression> enigmasProgressionList = progressionList.get(indexOfGeoGroupId).getEnigmasProgression();

                    int indexOfEnigmaId = IntStream.range(0, enigmasProgressionList.size())
                            .filter(i -> enigmasProgressionList.get(i).getEnigmaId().equals(currentTeam.getCurrentEnigmaId()))
                            .findAny()
                            .orElse(-1);

                    usedHintsIds = enigmasProgressionList.get(indexOfEnigmaId).getUsedHintsIds();


                    getHintsByEnigmaId(currentEnigmaId, token);

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
