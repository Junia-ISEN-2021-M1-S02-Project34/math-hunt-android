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
    }

    //TODO call dbb


    ArrayList<Hint> hintArrayList1 = new ArrayList<>();
    hintArrayList1.add(new Hint("Indice 1", "Texte indice", 10));
    hintArrayList1.add(new void Hint("Indice 2", "hint", 10));
    hintArrayList1.add(new void Hint("Indice 3", "hint", 10));

}