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
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HintFragment extends Fragment {

    private ListView  hintListView;
    private HintAdapter hintAdapter;
    private String teamId;
    private String currentEnigmaId;
    private ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();



        hintListView = (ListView) mView.findViewById(R.id.hintListView);

        ArrayList<Hint> hintList = new ArrayList<>();

        hintList.add(new Hint("Hint 1","Voici le première indice",150));
        hintList.add(new Hint("Hint 2","Voici le deuxième indicxe",150));

        hintAdapter = new HintAdapter(getActivity(),hintList);
        hintListView.setAdapter(hintAdapter);

        return mView;
    }

    private void getHintsByEnigmaId(String id) {
        Call<Hint> call = RetrofitClient.getInstance().getMathHuntApiService().getHintsByEnigmaId(id);
        call.enqueue(new Callback<Hint>() {
            @Override
            public void onResponse(Call<Hint> call, Response<Hint> response) {

                try {
                    progressDialog.dismiss();

                    Hint hint = response.body();



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Hint> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }


}
