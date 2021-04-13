package com.isen.math_hunt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.HintAdapter;
import com.isen.math_hunt.adapters.RankAdapter;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.entities.Team;

import java.util.ArrayList;

public class RankingFragment extends Fragment {
    private ListView rankListView;
    private RankAdapter rankAdapter;

    public RankingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_ranking, null);

        rankListView = (ListView) mView.findViewById(R.id.rankList);
        ArrayList<Team> teamList = new ArrayList<>();



        rankAdapter = new RankAdapter(getActivity(),teamList);
        rankListView.setAdapter(rankAdapter);

        return mView;
    }


}
