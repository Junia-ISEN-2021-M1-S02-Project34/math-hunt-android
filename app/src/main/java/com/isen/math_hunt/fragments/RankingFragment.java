package com.isen.math_hunt.fragments;

import android.app.ProgressDialog;
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
import com.isen.math_hunt.adapters.RankAdapter;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.RankingResponse;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingFragment extends Fragment {
    private ListView rankListView;
    private RankAdapter rankAdapter;

    private String token;
    private String gameId;

    private ProgressDialog progressDialog;

    private ProgressBar rankingProgressBar;

    public RankingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_ranking, null);


        rankingProgressBar = (ProgressBar) mView.findViewById(R.id.rankingProgressBar);
        rankingProgressBar.setVisibility(View.VISIBLE);


        rankListView = (ListView) mView.findViewById(R.id.rankList);
        ArrayList<Team> teamList = new ArrayList<>();


        token = getArguments().getString("ACCESS_TOKEN");
        gameId = getArguments().getString("GAME_ID");

        getRanking(gameId, token);


        return mView;
    }

    private void getRanking(String id, String token) {
        Call<RankingResponse> call = RetrofitClient.getInstance().getMathHuntApiService().getRanking(id, token);
        call.enqueue(new Callback<RankingResponse>() {
            @Override
            public void onResponse(Call<RankingResponse> call, Response<RankingResponse> response) {

                rankingProgressBar.setVisibility(View.INVISIBLE);
                try {
                    RankingResponse rank = response.body();
                    Log.d("getRanking", "onResponse: " + rank.getRankList().get(0).getUserName());

                    rankAdapter = new RankAdapter(getActivity(), rank.getRankList());
                    rankListView.setAdapter(rankAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<RankingResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }


}
