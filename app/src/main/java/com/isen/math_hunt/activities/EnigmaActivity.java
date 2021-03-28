package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Proposition;

import java.util.ArrayList;

public class EnigmaActivity extends AppCompatActivity {


    private ListView enigmaListView;
    private QcmAdapter qcmAdapter;
    private TextInputLayout answerTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigma);

        enigmaListView = (ListView) findViewById(R.id.answersListView);
        answerTextField = (TextInputLayout) findViewById(R.id.answerTextField);

        ArrayList<Proposition> propositionArrayList = new ArrayList<>();

        propositionArrayList.add(new Proposition(1,"Coucou","1212"));
        propositionArrayList.add(new Proposition(1,"Coucou","1212"));
        propositionArrayList.add(new Proposition(1,"Coucou","1212"));

        Answer answerQCM = new Answer(false,propositionArrayList);

        if (answerQCM.isMcq()){
            answerTextField.setVisibility(View.GONE);
            qcmAdapter = new QcmAdapter(this,answerQCM.getPropositionList());
            enigmaListView.setAdapter(qcmAdapter);
        }else {
            enigmaListView.setVisibility(View.GONE);
        }






    }
}