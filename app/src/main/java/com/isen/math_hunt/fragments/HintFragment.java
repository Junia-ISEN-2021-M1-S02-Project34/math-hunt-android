package com.isen.math_hunt.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.HintAdapter;
import com.isen.math_hunt.entities.Hint;

import java.util.ArrayList;

public class HintFragment extends Fragment {

    private ListView  hintListView;
    private HintAdapter hintAdapter;

    public HintFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.fragment_hint, null);

        hintListView = (ListView) mView.findViewById(R.id.hintListView);
        ArrayList<Hint> hintList = new ArrayList<>();

        hintList.add(new Hint("Hint 1","Voici le première indice",150));
        hintList.add(new Hint("Hint 2","Voici le deuxième indicxe",150));

        hintAdapter = new HintAdapter(getActivity(),hintList);
        hintListView.setAdapter(hintAdapter);

        return mView;
    }
}
