package com.isen.math_hunt.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.interfaces.DataTransferInterface;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnigmaActivity extends AppCompatActivity implements DataTransferInterface {


    private ListView enigmaListView;
    private TextView scoreTextView;
    private TextView enigmaTitleTextView;
    private TextView questionTextView;
    private TextView descriptionEnigma;
    private QcmAdapter qcmAdapter;
    private TextInputLayout answerTextField;
    private List<Proposition> propositionList = new ArrayList<>();
    private Button validateButton;
    private ProgressDialog progressDialog;
    private String currentMcqAnswerValue="";
    private boolean currentMcqAnswerIsChecked;
    private String currentEnigma;
    private int enigmaScoreValue;
    private String nextEnigmaAddress = "ISEN LILLE";
    private String userAnswer;
    private Boolean isMcq = false;
    private String enigmaAnswer;


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

        Bundle b = getIntent().getExtras();
        currentEnigma = b.getString("nextEnigmaId");

        getFullEnigmaById(currentEnigma);


        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isMcq){
                    userAnswer = currentMcqAnswerValue;

                }else userAnswer = answerTextField.getEditText().getText().toString();

                if (userAnswer.equals(enigmaAnswer)){
                    AlertDialog alertDialog = createGoodAnswerDialog();
                    alertDialog.show();
                }


                 else if (userAnswer.isEmpty()){
                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(EnigmaActivity.this);

                    builder.setTitle("Oups ...");
                    builder.setMessage("Il semble que ta réponse est vide !");
                    builder.setCancelable(false);
                    builder
                            .setPositiveButton(
                                    "Réésayer",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which)
                                        {
                                            // When the user click yes button
                                            // then app will close
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    AlertDialog alertDialog = createBadAnswerDialog();
                    alertDialog.show();
                }

            }
        });
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
                    enigmaAnswer = fullEnigma.getAnswer().getSolution();
                    if (fullEnigma.getAnswer().isMcq()) {
                        enigmaListView.setVisibility(View.VISIBLE);
                        propositionList = fullEnigma.getProposition();
                        qcmAdapter = new QcmAdapter(EnigmaActivity.this, propositionList, EnigmaActivity.this, EnigmaActivity.this);
                        enigmaListView.setAdapter(qcmAdapter);
                        isMcq=true;
                    } else {
                        answerTextField.setVisibility(View.VISIBLE);

                    }


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


    /**
     * get data from radioButton QCM Adapter
     *
     * @param isChecked
     * @param value
     */
    @Override
    public void onSetValues(Boolean isChecked, String value) {
        this.currentMcqAnswerIsChecked = isChecked;
        this.currentMcqAnswerValue = value;
    }

    public AlertDialog createGoodAnswerDialog(){
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(EnigmaActivity.this);

        builder.setTitle("Bravo vous avez réussi cette enigme");
        builder.setMessage("vous avez gagné " + enigmaScoreValue + " point \n" +
                "Maintenant rendez vous ici : " + nextEnigmaAddress);
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Continuer",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                // When the user click yes button
                                // then app will close
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    public AlertDialog createBadAnswerDialog(){
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(EnigmaActivity.this);

            builder.setTitle("Et mince vous êtes nul");
        builder.setMessage("Vous avez raté cette enigme");
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Continuer",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                // When the user click yes button
                                // then app will close
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }
}