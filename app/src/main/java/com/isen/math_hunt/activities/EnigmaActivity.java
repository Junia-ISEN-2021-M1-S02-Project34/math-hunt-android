package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.model.EnigmaList;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnigmaActivity extends AppCompatActivity {


    private ListView enigmaListView;
    private TextView scoreTextView;
    private TextView enigmaRankTextView;
    private TextView enigmaTitleTextView;
    private TextView questionTextView;
    private TextView descriptionEnigma;
    private QcmAdapter qcmAdapter;
    private TextInputLayout answerTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigma);

        enigmaListView = (ListView) findViewById(R.id.answersListView);
        answerTextField = (TextInputLayout) findViewById(R.id.answerTextField);
        scoreTextView = (TextView) findViewById(R.id.scoreTextField);
        enigmaTitleTextView = (TextView) findViewById(R.id.enigmaTitleTextField);
        descriptionEnigma = (TextView) findViewById(R.id.enigmaDescriptionTextField);
        questionTextView = (TextView) findViewById(R.id.questionTextField);

        getEnigmasById("606321de67829e7540fbea1b");
        //getAnswerByEnigmaId("606321de67829e7540fbea1b");
        ArrayList<Proposition> propositionArrayList = new ArrayList<>();

        propositionArrayList.add(new Proposition("1", "Coucou", "1212"));
        propositionArrayList.add(new Proposition("1", "Coucou", "1212"));
        propositionArrayList.add(new Proposition("1", "Coucou", "1212"));

        /*


        Answer answerQCM = new Answer(true, propositionArrayList);

        if (answerQCM.isMcq()) {
            answerTextField.setVisibility(View.GONE);
            qcmAdapter = new QcmAdapter(this, answerQCM.getPropositionList());
            enigmaListView.setAdapter(qcmAdapter);
        } else {
            enigmaListView.setVisibility(View.GONE);
        }

         */

    }

    private void getEnigmasById(String id) {
        Call<Enigma> call = RetrofitClient.getInstance().getMathHuntApiService().getEnigmaById(id);
        call.enqueue(new Callback<Enigma>() {
            @Override
            public void onResponse(Call<Enigma> call, Response<Enigma> response) {
                Enigma enigma = response.body();
                scoreTextView.setText(Integer.toString(enigma.getScoreValue()));
                enigmaTitleTextView.setText(enigma.getName());
                questionTextView.setText(enigma.getQuestion());
                descriptionEnigma.setText(enigma.getDescription());

            }

            @Override
            public void onFailure(Call<Enigma> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAnswerByEnigmaId(String id) {
        Call<Answer> call = RetrofitClient.getInstance().getMathHuntApiService().getAnswerByEnigmaId(id);
        call.enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                Answer answer = response.body();
                Log.e("ANSWER", String.valueOf(answer));
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}