package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.adapters.HintAdapter;

import java.util.ArrayList;

public class HintActivity extends AppCompatActivity {

    private ListView  hintListView;
    private HintAdapter hintAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_activity);

        hintListView = (ListView) findViewById(R.id.hintListView);
        ArrayList<Hint> hintList = new ArrayList<>();

        hintList.add(new Hint("Hint 1","Voici le première indice",150));
        hintList.add(new Hint("Hint 1","Voici le première indice",150));

        hintAdapter = new HintAdapter(this,hintList);
        hintListView.setAdapter(hintAdapter);



    }




}