package com.isen.math_hunt.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.activities.GameActivity;
import com.isen.math_hunt.activities.GeoGroupActivity;
import com.isen.math_hunt.activities.LoginActivity;
import com.isen.math_hunt.activities.SuccessActivity;
import com.isen.math_hunt.activities.WaitingActivity;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.interfaces.CurrentEnigmaIdInterface;
import com.isen.math_hunt.interfaces.RadioButtonDataTransfertInterface;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.ProgressionPost;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnigmaFragment extends Fragment implements RadioButtonDataTransfertInterface {

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
    private String currentMcqAnswerValue = "";
    private boolean currentMcqAnswerIsChecked;
    private int enigmaScoreValue;
    private String nextEnigmaAddress = "ISEN LILLE";
    private String userAnswer;
    private Boolean isMcq = false;
    private String enigmaAnswer;
    private int oldScore;
    private int newScore;
    private int currentEnigmaScore;


    private String teamId;
    private String currentEnigmaId;
    private String currentGeoGroupId;
    private String nextGeoGroup;

    private CurrentEnigmaIdInterface currentEnigmaIdInterface = (CurrentEnigmaIdInterface) getActivity();


    public EnigmaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        View mView = inflater.inflate(R.layout.fragment_enigma, null);

        enigmaListView = (ListView) mView.findViewById(R.id.answersListView);
        answerTextField = (TextInputLayout) mView.findViewById(R.id.answerTextField);
        scoreTextView = (TextView) mView.findViewById(R.id.scoreTextField);
        enigmaTitleTextView = (TextView) mView.findViewById(R.id.enigmaTitleTextField);
        descriptionEnigma = (TextView) mView.findViewById(R.id.enigmaDescriptionTextField);
        questionTextView = (TextView) mView.findViewById(R.id.questionTextField);
        validateButton = (Button) mView.findViewById(R.id.validateButton);
        enigmaListView.setVisibility(View.GONE);
        answerTextField.setVisibility(View.GONE);


        teamId = getArguments().getString("TEAM_ID");
        currentEnigmaId = getArguments().getString("CURRENT_ENIGMA_ID");
        currentGeoGroupId = getArguments().getString("CURRENT_GEOGROUP_ID");

        Log.d("YOLOLO", "onCreateView: " +currentGeoGroupId);
        getFullEnigmaById(currentEnigmaId);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newScore = oldScore + currentEnigmaScore;
                Log.d("Score", "onClick: " + newScore);

                ProgressionPost progressionPost = new ProgressionPost(currentEnigmaId, newScore);

                if (isMcq) {
                    userAnswer = currentMcqAnswerValue.toLowerCase();

                } else
                    userAnswer = answerTextField.getEditText().getText().toString().toLowerCase();

                if (userAnswer.equals(enigmaAnswer.toLowerCase())) {

                    updateTeamProgression(teamId, progressionPost);


                } else if (userAnswer.isEmpty()) {
                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(getActivity());

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
                                                            int which) {
                                            // When the user click yes button
                                            // then app will close
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    AlertDialog alertDialog = createBadAnswerDialog();
                    alertDialog.show();
                }

            }
        });


        return mView;

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
                    currentEnigmaScore = fullEnigma.getEnigma().getScoreValue();
                    if (fullEnigma.getAnswer().isMcq()) {
                        enigmaListView.setVisibility(View.VISIBLE);
                        propositionList = fullEnigma.getProposition();
                        propositionList.add(new Proposition(fullEnigma.getAnswer().getSolution()));
                        Collections.shuffle(propositionList);
                        qcmAdapter = new QcmAdapter(getActivity(), propositionList, getActivity(), EnigmaFragment.this);
                        enigmaListView.setAdapter(qcmAdapter);
                        isMcq = true;
                    } else {
                        answerTextField.setVisibility(View.VISIBLE);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<FullEnigma> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }

    private void updateTeamProgression(String id, ProgressionPost progressionPost) {


        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().updateTeamProgression(id, progressionPost);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team currentTeam = response.body();
                    Log.d("FINISH", "onResponse: " + currentTeam.getGameFinished());

                    if (currentTeam.getGameFinished()) {
                        Intent intent = new Intent(getContext(), SuccessActivity.class);
                        Bundle b = new Bundle();
                        b.putString("TEAM_ID", teamId);
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                    } else {
                        Log.d("TAG", "onResponse: " + response);
                        currentEnigmaId = currentTeam.getCurrentEnigmaId();
                        nextGeoGroup = currentTeam.getCurrentGeoGroupId();


                        AlertDialog alertDialog = createGoodAnswerDialog();
                        alertDialog.show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
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

    public AlertDialog createGoodAnswerDialog() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity());

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
                                                int which) {

                                Intent intent;//Put your id to your next Intent
                                Log.d("coucou", "onClick: " + currentGeoGroupId);
                                Log.d("coucou", "onClick: " + nextGeoGroup);

                                if (!currentGeoGroupId.equals(nextGeoGroup)){
                                    intent = new Intent(getContext(), GeoGroupActivity.class);

                                }else{
                                    intent = new Intent(getContext(), GameActivity.class);

                                }
                                Bundle b = new Bundle();
                                b.putString("TEAM_ID", teamId);
                                intent.putExtras(b); //Put your id to your next Intent
                                startActivity(intent);
                                dialog.cancel();

                            }
                        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    public AlertDialog createBadAnswerDialog() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity());

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
                                                int which) {
                                // When the user click yes button
                                // then app will close
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }
}