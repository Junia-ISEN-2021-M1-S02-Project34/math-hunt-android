package com.isen.math_hunt.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.interfaces.CurrentEnigmaIdInterface;
import com.isen.math_hunt.interfaces.RadioButtonDataTransfertInterface;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
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

    private String teamId;
    private String currentEnigmaId;
    private String currentGeoGroupId;

    private CurrentEnigmaIdInterface listener = (CurrentEnigmaIdInterface) getActivity();


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

        getFullEnigmaById(currentEnigmaId);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isMcq) {
                    userAnswer = currentMcqAnswerValue;

                } else
                    userAnswer = answerTextField.getEditText().getText().toString().toLowerCase();

                if (userAnswer.equals(enigmaAnswer.toLowerCase())) {
                    AlertDialog alertDialog = createGoodAnswerDialog();
                    alertDialog.show();
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
                    if (fullEnigma.getAnswer().isMcq()) {
                        enigmaListView.setVisibility(View.VISIBLE);
                        propositionList = fullEnigma.getProposition();
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
                                // When the user click yes button
                                // then app will close
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