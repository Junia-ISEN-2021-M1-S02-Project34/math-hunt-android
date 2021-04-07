package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.model.EnigmaList;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

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
    private List<Proposition> propositionList = new ArrayList<>();
    private Button validateButton;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        setContentView(R.layout.activity_enigma);
        enigmaListView = (ListView) findViewById(R.id.answersListView);
        answerTextField = (TextInputLayout) findViewById(R.id.answerTextField);
        scoreTextView = (TextView) findViewById(R.id.scoreTextField);
        enigmaTitleTextView = (TextView) findViewById(R.id.enigmaTitleTextField);
        descriptionEnigma = (TextView) findViewById(R.id.enigmaDescriptionTextField);
        questionTextView = (TextView) findViewById(R.id.questionTextField);
        validateButton = (Button) findViewById(R.id.validateButton);
        enigmaListView.setVisibility(View.GONE);
        answerTextField.setVisibility(View.GONE);


        //getEnigmasById("606321de67829e7540fbea1b");
        getFullEnigmaById("6063223667829e7540fbea1f");
        //getAnswerByEnigmaId("606321de67829e7540fbea1b");

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

    private void getFullEnigmaById(String id) {
        Call<FullEnigma> call = RetrofitClient.getInstance().getMathHuntApiService().getFullEnigmaById(id);
        call.enqueue(new Callback<FullEnigma>() {
            @Override
            public void onResponse(Call<FullEnigma> call, Response<FullEnigma> response) {

                try {
                    progressDialog.dismiss();

                    FullEnigma fullEnigma = response.body();
                    scoreTextView.setText(Integer.toString(fullEnigma.getEnigma().getScoreValue()));
                    enigmaTitleTextView.setText(fullEnigma.getEnigma().getName());
                    questionTextView.setText(fullEnigma.getEnigma().getQuestion());
                    descriptionEnigma.setText(fullEnigma.getEnigma().getDescription());
                    if (fullEnigma.getAnswer().isMcq()) {
                        enigmaListView.setVisibility(View.VISIBLE);

                        propositionList = fullEnigma.getProposition();
                        qcmAdapter = new QcmAdapter(EnigmaActivity.this, propositionList);
                        enigmaListView.setAdapter(qcmAdapter);
                    } else {
                        answerTextField.setVisibility(View.VISIBLE);

                    }

                    validateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<FullEnigma> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }

    private void getEnigmasById(String id) {
        Call<Enigma> call = RetrofitClient.getInstance().getMathHuntApiService().getEnigmaById(id);
        call.enqueue(new Callback<Enigma>() {
            @Override
            public void onResponse(Call<Enigma> call, Response<Enigma> response) {

                try {
                    Enigma enigma = response.body();
                    scoreTextView.setText(Integer.toString(enigma.getScoreValue()));
                    enigmaTitleTextView.setText(enigma.getName());
                    questionTextView.setText(enigma.getQuestion());
                    descriptionEnigma.setText(enigma.getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    public void switchActivity(Class activity) {
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);
    }


}