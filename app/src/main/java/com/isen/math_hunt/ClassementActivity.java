package com.isen.math_hunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ClassementActivity extends AppCompatActivity {

    private TextView mTitleText;
    private ListView listView;
    private RankAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        listView = (ListView) findViewById(R.id.rank_list);
        ArrayList<Rank> ranksList = new ArrayList<>();

        mAdapter = new RankAdapter(this,ranksList);
        listView.setAdapter(mAdapter);
    }
}