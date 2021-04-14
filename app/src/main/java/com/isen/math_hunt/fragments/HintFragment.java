package com.isen.math_hunt.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

    private List<String> usedHintsIds;


    public HintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_hint, null);
        teamId = getArguments().getString("TEAM_ID");
        currentEnigmaId = getArguments().getString("CURRENT_ENIGMA_ID");
        usedHintsIds = getArguments().getStringArrayList("USED_HINTS_IDS");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        getHintsByEnigmaId(currentEnigmaId);

        hintListView = (ListView) mView.findViewById(R.id.hintListView);

        return mView;
    }

    private void getHintsByEnigmaId(String id) {
        Call<HintList> call = RetrofitClient.getInstance().getMathHuntApiService().getHintsByEnigmaId(id);
        call.enqueue(new Callback<HintList>() {
            @Override
            public void onResponse(Call<HintList> call, Response<HintList> response) {

                try {
                    progressDialog.dismiss();

                    HintList hints = response.body();

                    hintList = hints.getHints();

                    hintAdapter = new HintAdapter(getActivity(),hintList, usedHintsIds);
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




}
